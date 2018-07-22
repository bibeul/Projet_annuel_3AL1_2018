const express = require('express');
const controllers = require('../controllers');
const bodyParser = require('body-parser');
const UserController = controllers.UserController;
const MapController = controllers.MapController;
const fs = require('fs');
const jwt = require('jsonwebtoken');
const del = require('del');
const path = require('path');
const archiver = require('archiver');
const fileUpload = require('express-fileupload');
const unzip = require('unzip2');
var AdmZip = require('adm-zip');


const MapRouter = express.Router();
MapRouter.use(bodyParser.json());
MapRouter.use(fileUpload());

MapRouter.put('/', UserController.isLogged ,function(req, res){
    const name = req.body.name;
    const description = req.body.description;
    const user = req.body.user_id;

    if(name === undefined || description === undefined){
        res.status(400).end();
        return;
    }

    MapController.setMap(name, description, user)
        .then((successfullyAdd) => {
        res.status(201).json(successfullyAdd);
        res.end();
    })
    .catch((err) => {
        console.log(err);
        res.status(500).end();
    });
});

MapRouter.get('/displayAll', function(req, res){
   MapController.getAllMap().then((succesfullDisplay) => {
       res.status(200).json(succesfullDisplay);
       res.end();
   })
    .catch((err) => {
       res.status(500).end();
    });
});

MapRouter.get('/download/:mapname', function(req, res){
    const mapname = req.params.mapname;
    const pathfile = path.join(__dirname,'../Maps/',mapname);
    if(fs.existsSync(pathfile)){
        const archive = archiver('zip');
        archive.pipe(res);
        archive.directory(pathfile,false);
        archive.finalize();

        res.setHeader('Content-Type','application/zip');
        res.setHeader('Content-Disposition','attachment; filename='+mapname+'.zip');

        res.status(200);
    } else {
        res.status(404).end();
    }


});

MapRouter.get('/downloadPic/:mapname', function(req, res){
    const mapname = req.params.mapname;
    const pathfile = path.join(__dirname,'../Maps/'+mapname,mapname+'Image.jpg');
    if(fs.existsSync(pathfile)){
        const data = fs.statSync(pathfile);

        res.setHeader('Content-Type','image/jpg');
        res.setHeader('Content-length', data.size);
        res.setHeader('Content-Disposition','attachment; filename='+mapname+'Image.jpg');

        var stream = fs.createReadStream(pathfile);
        stream.pipe(res);
    } else {
        res.status(404).end();
    }
});

MapRouter.post('/upload', UserController.isLogged, function(req, res){

    const mapname = req.body.mapname;
    const mapdesc = req.body.description;
    const mapfile = req.files.mapfile;
    const mapsprites = req.files.mapsprites;
    const mapriddle = req.files.mapriddle;
    const mapriddle0 = req.files.mapriddle0;
    const mapriddle1 = req.files.mapriddle1;
    const userid = jwt.decode(req.headers['x-access-token']).id;

    var mapjson = {};

    if(mapname === undefined || mapdesc === undefined || mapfile === undefined || mapsprites === undefined || userid === undefined || mapriddle === undefined){
        res.status(400).send("Parameter undefined").end();
        return;
    }
    const pathfile = path.join(__dirname,'../Maps/',mapname);

    if (!fs.existsSync(pathfile)){
        fs.mkdirSync(pathfile);
        fs.mkdirSync(pathfile+'/Enigme');



        mapfile.mv(pathfile + '/map.tmx',function(err){
            if(err){
                console.log(err);
                del([pathfile]);
                return res.status(400).end();
            }
        });

        mapriddle.mv(pathfile + '/Enigme/' + mapriddle.name,function(err){
            if(err){
                console.log(err);
                del([pathfile]);
                return res.status(400).end();
            }
        });

        if(mapriddle0 !== undefined){
            mapriddle0.mv(pathfile + '/Enigme/' + mapriddle0.name,function(err){
                if(err){
                    console.log(err);
                    del([pathfile]);
                    return res.status(400).end();
                }
            });
        }
        if(mapriddle1 !== undefined){
            mapriddle1.mv(pathfile + '/Enigme/' + mapriddle1.name,function(err){
                if(err){
                    console.log(err);
                    del([pathfile]);
                    return res.status(400).end();
                }
            });
        }

        mapsprites.mv(pathfile + '/' + mapname + 'Sprites.zip',function(err){
            if(err){
                console.log(err);
                del([pathfile]);
                return res.status(400).end();
            }

            var zip = new AdmZip(pathfile + '/' + mapname + 'Sprites.zip');
            zip.extractAllTo(pathfile);
        });



        MapController.getMapName(mapname).then((name) => {
            if(name != null){
            res.status(409).end()
            }else{
                MapController.setMap(mapname,mapdesc,userid).then((mapCreated) => {
                    var mapjson = {};
                    mapjson.id = mapCreated.id;
                    mapjson.name = mapCreated.name;
                    mapjson.description = mapCreated.description;
                    mapjson.userid = mapCreated.user_id;
                    fs.writeFile(pathfile + '/' + mapname + '.json', JSON.stringify(mapjson) , 'utf8',(err) => {
                        if(err) throw err;
                    });
                }).catch((err) => {
                    del([pathfile]);
                    return res.status(400);
            });
            }
        });
        fs.writeFile(pathfile + '/plugin.json' ,'','utf8',(err) => {
            if(err) return res.status(400).end();
        });
        res.status(201).send('map upload').end();


    }else{
        res.status(409).send('map already exist').end();
    }

});

//MapRouter.delete('/delete/:mapname', /* add checkAdmin function in user controller */ , function(req, res){
//    const mapname = req.params.mapname;
//});

module.exports = MapRouter;