const express = require('express');

const types = require('../db/types');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    types.getAll().then(types => {
        res.json(types);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    types.getOne(req.params.id).then(type => {
        if(type) {
            res.json(type);
        } else {
            next();
        }
    });
});

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
    types.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;