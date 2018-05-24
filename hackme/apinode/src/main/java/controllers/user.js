const UserController = function () {};
const ModelIndex = require('../models');
const User = ModelIndex.User;
const bcrypt = require('bcrypt');
const config = require('../config');
const jwt = require('jsonwebtoken');

UserController.register = function(name, email, password){
    return User.create({
        email: email,
        username: name,
        password: bcrypt.hashSync(password,10)
    })
};

UserController.getUser = function(email){
    const options = {
        where: {
            email: email
        }
    };
    return User.find(options);
};

UserController.sign_in = function(email, password){
    return UserController.getUser(email).then((usr)=>{
        let passwordIsValid = bcrypt.compareSync(password, usr.password);
    if(passwordIsValid){
        var token = jwt.sign({ id: usr.id, email: usr.email, username: usr.username, score: usr.score },
            config.secret,
            {expiresIn: 86400 // expires in 24 hours
            });
        return token;
    }else{
        return -1;
    }
})
.catch((err) => {
        console.log(err);
    return -1;
});
};

UserController.isLogged = function(req){
    const token = req.headers['x-access-token'];
    return jwt.verify(token,config.secret,function(err, decoded){
        if(err) return false;//res.status(500).send({ auth: false, message: 'Failed to authenticate token.' });

        return UserController.getUser(decoded.email).then((isLog) => {
            return true;
    })
    .catch((err) => {
            console.log(err);
        return false;
    });
    });
};

module.exports = UserController;

