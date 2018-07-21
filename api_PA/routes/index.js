const RouteManager = function() { };
const cors = require('cors');

RouteManager.attach = function(app) {
    app.use(cors({origin: '*'}));
    app.use('/user', require('./user'));
    app.use('/map', require('./map'));
    app.use('/plugin', require('./plugin'));
    app.use('/score', require('./score'));
};

module.exports = RouteManager;
