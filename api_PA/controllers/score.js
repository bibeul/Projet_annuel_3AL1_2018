const ScoreController = function () {};
const ModelIndex = require('../models');
const Score = ModelIndex.Score;

ScoreController.setScore = function(iduser, idmap, score){
    return Score.create({
        score: score,
        user_id: iduser,
        map_id: idmap
    });
};

ScoreController.getAllScore = function(){
    return Score.findAll();
};

ScoreController.getScoreMap = function(idmap){
    const options = {
        where: {
            map_id: idmap
        }
    };
    return Score.findAll(options);
};

ScoreController.getScoreMapUser = function(idmap, iduser){
    const options = {
        where: {
            map_id: idmap,
            user_id: iduser
        }
    };
    return Score.find(options);
};

ScoreController.getScoreUser = function(iduser){
    const options = {
        where: {
            user_id: iduser
        }
    };
    return Score.findAll(options);
};

ScoreController.setNewScore = function(iduser, idmap, score){
    return Score.update({
        score: score
    },{
        where: {
            map_id: idmap,
            user_id: iduser
        }
    });
};

module.exports = ScoreController;