module.exports = function(sequelize, DataTypes) {
    const Map = sequelize.define('Map', {
        id: {
            type: DataTypes.BIGINT,
            primaryKey: true,
            autoIncrement: true,
            unique: true
        },
        name: {
            type: DataTypes.STRING,
            unique: true,
            allowNull: false
        },
        description: {
            type: DataTypes.STRING,
            allowNull: true
        }
    }, {
        paranoid: true,
        underscored: true,
        freezeTableName: true
    });
    Map.associate = _associate;
    return Map;
};

// INTERNAL FUNCTIONS
function _associate(models) {
    models.Map.belongsTo(models.User);
}