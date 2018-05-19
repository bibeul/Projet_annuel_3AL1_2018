const RouteManager = function() { };

RouteManager.attach = function(app) {
    app.use('/user', require('./user'));
    app.use('/map', require('./map'));
    app.use('/plugin', require('./plugin'));
};

module.exports = RouteManager;
