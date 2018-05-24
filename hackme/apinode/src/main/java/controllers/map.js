const MapController = function () {};
const ModelIndex = require('../models');
const Map = ModelIndex.Map;

MapController.setMap = function(name, desc){
    return Map.create({
        name: name,
        description: desc
    });
};

module.exports = MapController;