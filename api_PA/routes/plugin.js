const express = require('express');
const controllers = require('../controllers');
const bodyParser = require('body-parser');
const PluginController = controllers.PluginController;
const UserController = controllers.UserController;
const fs = require('fs');
const jwt = require('jsonwebtoken');
const del = require('del');
const path = require('path');
const fileUpload = require('express-fileupload');


const PluginRouter = express.Router();
PluginRouter.use(bodyParser.json());
PluginRouter.use(fileUpload());

PluginRouter.put('/', UserController.isLogged, function(req, res){
    const name = req.body.name;
    const description = req.body.description;
    const user = req.body.user_id;

    if(name === undefined || description === undefined){
        res.status(400).end();
        return;
    }

    PluginController.setPlugin(name, description, user)
        .then((successfullyAdd) => {
        res.status(201).json(successfullyAdd);
        res.end();
    })
    .catch((err) => {
        console.log(err);
        res.status(500).end();
    });
});

PluginRouter.get('/displayAll', function(req, res){
    PluginController.getAllPlugin().then((succesfullDisplay) => {
        res.status(200).json(succesfullDisplay);
    res.end();
    })
    .catch((err) => {
        res.status(500).end();
    });
});

PluginRouter.post('/upload', UserController.isLogged, function(req, res){

    const pluginname = req.body.pluginname;
    const plugindesc = req.body.description;
    const pluginjar = req.files.pluginfile;

    const userid = jwt.decode(req.headers['x-access-token']).id;

    if(pluginname === undefined || plugindesc === undefined || pluginjar === undefined||userid === undefined){
        res.status(400).end();
        return;
    }
    const pathfile = path.join(__dirname,'../Plugins/');

    if (!fs.existsSync(path.join(pathfile,pluginname+'.jar'))){

        PluginController.getPluginName(pluginname).then((name) => {
            if(name != null){
            res.status(409).end()
        }else{
            PluginController.setPlugin(pluginname, plugindesc, userid).then((successfullyAdd) => {
                pluginjar.mv(pathfile + '/' + pluginname + '.jar', function (err) {
                if (err) {
                    console.log(err);
                    return res.status(400).end();
                }
            });
        })
        .catch((err) => {
                console.log(err);
            res.status(500).end();
        });
        }
    });
        res.status(201).send('plugin uploaded').end();
    }else{
        res.status(409).send('plugin already exist').end();
    }
});

PluginRouter.get('/download/:pluginname', function(req, res){
    const pluginname = req.params.pluginname;
    const pathfile = path.join(__dirname,'../Plugins/',pluginname+'.jar');

    if(fs.existsSync(pathfile)){
        res.setHeader('Content-Type','application/java-archive');
        res.setHeader('Content-Disposition','attachment; filename='+pluginname+'.jar');
        var readStream = fs.createReadStream(pathfile);
        readStream.pipe(res);

        res.status(200);
    } else {
        res.status(404).end();
    }


});

module.exports = PluginRouter;