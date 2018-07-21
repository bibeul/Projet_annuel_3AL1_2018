const MapController = function () {};
const ModelIndex = require('../models');
const fs = require('fs');
const Map = ModelIndex.Map;

MapController.setMap = function(name, desc, user){
    return Map.create({
        name: name,
        description: desc,
        user_id: user
    });
};

MapController.getAllMap = function(){
    return Map.findAll();
};

MapController.getMapName = function(name){
    const options = {
        where: {
            name: name
        }
    };
    return Map.find(options);
};


module.exports = MapController;