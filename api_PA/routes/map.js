const express = require('express');
const controllers = require('../controllers');
const bodyParser = require('body-parser');
const UserController = controllers.UserController;
const MapController = controllers.MapController;

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

module.exports = MapRouter;