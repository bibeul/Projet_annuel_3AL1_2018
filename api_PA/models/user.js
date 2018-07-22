module.exports = function(sequelize, DataTypes) {
    const User = sequelize.define('User', {
        id: {
            type: DataTypes.BIGINT,
            primaryKey: true,
            autoIncrement: true,
            unique: true
        },
        email: {
            type: DataTypes.STRING,
            unique: true,
            allowNull: false
        },
        username: {
            type: DataTypes.STRING,
            allowNull: false
        },
        password: {
            type: DataTypes.STRING,
            allowNull: false
        },
        admin: {
            type: DataTypes.INTEGER,
            defaultValue: 0
        }
    }, {
        paranoid: true,
        underscored: true,
        freezeTableName: true
    });
    //User.associate = _associate;
    return User;
};

// INTERNAL FUNCTIONS
function _associate(models) {
}