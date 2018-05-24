const express = require('express');
const controllers = require('../controllers');
const bodyParser = require('body-parser');
const UserController = controllers.UserController;

const UserRouter = express.Router();
UserRouter.use(bodyParser.json());

UserRouter.put('/',function(req, res){
    const name= req.body.username;
    const email= req.body.email;
    const password= req.body.password;

    if(name === undefined || email === undefined || password === undefined){
        res.status(400).end();
        return;
    }

    UserController.register(name, email, password)
        .then((successfullyAdd) => {
        res.status(201).json(successfullyAdd);
    res.end();
})
.catch((err) => {
        console.log(err);
    res.status(500).end();
});
});

UserRouter.post('/signin',function(req, res){
    const email = req.body.email;
    const password = req.body.password;

    if(email === undefined || password === undefined){
        res.status(400).send({ auth: false, message: 'Could not find password and/or email' }).end();
        return;
    }

    UserController.sign_in(email, password).then((succesfullLogin)=>{
        if(succesfullLogin == -1){
        res.status(401).send({ auth: false, message: 'Wrong password and/or email' }).end();
    }else{
        res.status(200).send({ auth: true, token: succesfullLogin }).end();
    }
    })
    .catch((err) => {
        console.log(err);
        res.status(500).end();
    });
});

UserRouter.get('/logout', function(req, res) {
    res.status(200).send({ auth: false, token: null });
});


module.exports = UserRouter;
