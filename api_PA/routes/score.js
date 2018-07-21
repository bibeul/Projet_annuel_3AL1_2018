const express = require('express');
const controllers = require('../controllers');
const bodyParser = require('body-parser');
const UserController = controllers.UserController;
const ScoreController = controllers.ScoreController;

const ScoreRouter = express.Router();
ScoreRouter.use(bodyParser.json());

ScoreRouter.put('/', /*UserController.isLogged, */function(req,res){
    const username = req.body.user;
    const mapname = req.body.map;
    const score = req.body.score;

    ScoreController.getScoreMapUser(mapname, username).then((mapscore) =>{
        if(mapscore != null){
            ScoreController.setNewScore(username,mapname,score).then((success) => {
                res.status(201).json(success).end();
            });
        }else{
            ScoreController.setScore(username,mapname,score).then((success) => {
                res.status(201).json(success).end();
            });
        }
    }).catch((err) => {
        return res.status(400).end();
    });
});

ScoreRouter.get('/displayAll', function(req, res){
    ScoreController.getAllScore().then((succesfullDisplay) => {
        res.status(200).json(succesfullDisplay);
        res.end();
    })
    .catch((err) => {
            res.status(500).end();
    });
});

ScoreRouter.get('/mapscore/:mapname', function(req, res){
    const map = req.params.mapname;

    ScoreController.getScoreMap(map).then((succesfullDisplay) => {
        res.status(200).json(succesfullDisplay);
        res.end();
    })
    .catch((err) => {
            res.status(500).end();
    });
});

ScoreRouter.get('/userscore/:username', function(req, res){
    const user = req.params.username;

    ScoreController.getScoreUser(user).then((succesfullDisplay) => {
        res.status(200).json(succesfullDisplay);
        res.end();
    })
    .catch((err) => {
            res.status(500).end();
    });
});

ScoreRouter.get('/mapscore/:mapname/:username', function(req, res){
    const map = req.params.mapname;
    const user = req.params.username;

    ScoreController.getScoreMapUser(map,user).then((succesfullDisplay) => {
        res.status(200).json(succesfullDisplay);
        res.end();
    })
    .catch((err) => {
            res.status(500).end();
    });
});


module.exports = ScoreRouter;