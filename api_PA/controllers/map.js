const MapController = function () {};
const ModelIndex = require('../models');
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

module.exports = MapController;