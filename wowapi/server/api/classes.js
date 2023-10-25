const express = require('express');

const classes = require('../db/classes');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    classes.getAll().then(classes => {
        res.json(classes);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    classes.getOne(req.params.id).then(classe => {
        if(classe) {
            res.json(classe);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validClass(req.body)) {
        classes.create(req.body).then(classes => {
            res.json(classes[0]);
        });
    } else {
        next(new Error('Invalid class'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validClass(req.body)) {
        classes.update(req.params.id, req.body).then(classes => {
            res.json(classes[0]);
        });
    } else {
        next(new Error('Invalid class'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    classes.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;