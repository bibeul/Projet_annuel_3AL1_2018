const ScoreController = function () {};
const ModelIndex = require('../models');
const Score = ModelIndex.Score;

ScoreController.setScore = function(user, map, score){
    return Score.create({
        score: score,
        mapname: map,
        username: user
    });
};

ScoreController.getAllScore = function(){
    return Score.findAll();
};

ScoreController.getScoreMap = function(map){
    const options = {
        where: {
            mapname: map
        }
    };
    return Score.findAll(options);
};

ScoreController.getScoreMapUser = function(map, user){
    const options = {
        where: {
            mapname: map,
            username: user
        }
    };
    return Score.find(options);
};

ScoreController.getScoreUser = function(user){
    const options = {
        where: {
            username: user
        }
    };
    return Score.findAll(options);
};

ScoreController.setNewScore = function(user, map, score){
    return Score.update({
        score: score
    },{
        where: {
            mapname: map,
            username: user
        }
    });
};

module.exports = ScoreController;