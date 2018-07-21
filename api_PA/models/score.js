module.exports = function(sequelize, DataTypes) {
    const Score = sequelize.define('Score', {
        id: {
            type: DataTypes.BIGINT,
            primaryKey: true,
            autoIncrement: true,
            unique: true
        },
        score: {
            type: DataTypes.BIGINT,
            unique: false,
            allowNull: false
        },
        mapname: {
            type: DataTypes.STRING,
            unique: false,
            allowNull: false
        },
        username: {
            type: DataTypes.STRING,
            unique: false,
            allowNull: false
        }
    }, {
        paranoid: true,
        underscored: true,
        freezeTableName: true
    });
    Score.associate = _associate;
    return Score;
};

// INTERNAL FUNCTIONS
function _associate(models) {
}