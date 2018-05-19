const express = require('express');
const controllers = require('../controllers');
const bodyParser = require('body-parser');
const PluginController = controllers.PluginController;

const PluginRouter = express.Router();
PluginRouter.use(bodyParser.json());

PluginRouter.put('/', function(req, res){
    const name = req.body.name;
    const description = req.body.description;

    if(name === undefined || description === undefined){
        res.status(400).end();
        return;
    }

    PluginController.setMap(name, description)
        .then((successfullyAdd) => {
        res.status(201).json(successfullyAdd);
        res.end();
    })
    .catch((err) => {
        console.log(err);
        res.status(500).end();
    });
});

module.exports = PluginRouter;