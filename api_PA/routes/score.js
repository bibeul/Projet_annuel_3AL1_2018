const express = require('express');
const controllers = require('../controllers');
const bodyParser = require('body-parser');
const UserController = controllers.UserController;
const ScoreController = controllers.ScoreController;

const ScoreRouter = express.Router();
ScoreRouter.use(bodyParser.json());

ScoreRouter.put('/', UserController.isLogged, function(req,res){
    const iduser = req.body.iduser;
    const idmap = req.body.idmap;
    const score = req.body.score;

    ScoreController.getScoreMapUser(idmap, iduser).then((mapscore) =>{
        if(mapscore != null){
            ScoreController.setNewScore(iduser,idmap,score).then((success) => {
                res.status(201).json(success).end();
            });
        }else{
            ScoreController.setScore(iduser,idmap,score).then((success) => {
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

ScoreRouter.get('/mapscore/:mapid', function(req, res){
    const mapid = req.params.mapid;

    ScoreController.getScoreMap(mapid).then((succesfullDisplay) => {
        res.status(200).json(succesfullDisplay);
        res.end();
    })
    .catch((err) => {
            res.status(500).end();
    });
});

ScoreRouter.get('/userscore/:userid', function(req, res){
    const userid = req.params.userid;

    ScoreController.getScoreUser(userid).then((succesfullDisplay) => {
        res.status(200).json(succesfullDisplay);
        res.end();
    })
    .catch((err) => {
            res.status(500).end();
    });
});

ScoreRouter.get('/:mapid/:userid', function(req, res){
    const mapid = req.params.mapid;
    const userid = req.params.userid;

    ScoreController.getScoreMapUser(mapid,userid).then((succesfullDisplay) => {
        res.status(200).json(succesfullDisplay);
        res.end();
    })
    .catch((err) => {
            res.status(500).end();
    });
});


module.exports = ScoreRouter;