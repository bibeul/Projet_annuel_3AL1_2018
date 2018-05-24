const express = require('express');
const controllers = require('../controllers');
const bodyParser = require('body-parser');
const PluginController = controllers.PluginController;
const UserController = controllers.UserController;

const PluginRouter = express.Router();
PluginRouter.use(bodyParser.json());

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

module.exports = PluginRouter;