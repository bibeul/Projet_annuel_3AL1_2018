const express = require('express');
const bodyParser = require('body-parser');
//const controllers = require('../controllers');
//const models = require('../models');


const mapRouter = express.Router();
mapRouter.use(bodyParser.json());


mapRouter.get('/', function(req, res) {
    res.json("first test");
});

mapRouter.get('/getMap', function(req, res){
    var testMap = [
        [0,0,0,0],
        [0,0,0,0],
        [0,0,0,0],
        [0,0,0,0]
    ];
    res.json(testMap);
})

module.exports = mapRouter;