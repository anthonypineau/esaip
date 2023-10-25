const express = require('express');

const users = require('../db/users');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    users.getAll().then(users => {
        res.json(users);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    users.getOne(req.params.id).then(user => {
        if(user) {
            res.json(user);
        } else {
            next();
        }
    });
});

/*
router.post('/', (req, res, next) => {
    if(validHandler.validType(req.body)) {
        types.create(req.body).then(types => {
            res.json(types[0]);
        });
    } else {
        next(new Error('Invalid type'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validType(req.body)) {
        types.update(req.params.id, req.body).then(clatypessses => {
            res.json(types[0]);
        });
    } else {
        next(new Error('Invalid type'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    medias.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});
*/

module.exports = router;