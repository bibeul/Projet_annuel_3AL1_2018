const express = require('express');
const controllers = require('../controllers');
const bodyParser = require('body-parser');
const UserController = controllers.UserController;
const MapController = controllers.MapController;
const fs = require('fs');
const path = require('path');

const MapRouter = express.Router();
MapRouter.use(bodyParser.json());

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
    const pathfile = path.join(__dirname,'../Maps/',mapname+'.zip');
    if(fs.existsSync(pathfile)){
        const data = fs.statSync(pathfile);

        res.setHeader('Content-Type','application/zip');
        res.setHeader('Content-length', data.size);
        res.setHeader('Content-Disposition','attachment; filename='+mapname+'.zip');

        var stream = fs.createReadStream(pathfile);
        stream.pipe(res);
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

module.exports = MapRouter;