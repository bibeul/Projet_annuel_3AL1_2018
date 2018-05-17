const mapRouter = require('./map');

const RouterManager = function() { };

RouterManager.attach = function(app) {
    app.use('/map', mapRouter);
};

module.exports = RouterManager;
