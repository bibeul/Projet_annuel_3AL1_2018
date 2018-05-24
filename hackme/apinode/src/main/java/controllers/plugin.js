const PluginController = function () {};
const ModelIndex = require('../models');
const Plugin = ModelIndex.Map;

PluginController.setPlugin = function(name, desc){
    return Plugin.create({
        name: name,
        description: desc
    });
};

module.exports = PluginController;