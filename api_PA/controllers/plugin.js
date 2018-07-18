const PluginController = function () {};
const ModelIndex = require('../models');
const Plugin = ModelIndex.Plugin;

PluginController.setPlugin = function(name, desc, user){
    return Plugin.create({
        name: name,
        description: desc,
        user_id: user
    });
};

PluginController.getAllPlugin = function(){
  return Plugin.findAll();
};

PluginController.getPluginName = function(name){
    const options = {
        where: {
            name: name
        }
    };
    return Plugin.find(options);
};

module.exports = PluginController;