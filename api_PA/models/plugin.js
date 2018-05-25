module.exports = function(sequelize, DataTypes) {
    const Plugin = sequelize.define('Plugin', {
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
    Plugin.associate = _associate;
    return Plugin;
};

// INTERNAL FUNCTIONS
function _associate(models) {
    models.Plugin.belongsTo(models.User);
}