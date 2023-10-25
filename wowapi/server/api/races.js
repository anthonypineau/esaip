const express = require('express');

const races = require('../db/races');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    races.getAll().then(races => {
        res.json(races);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    races.getOne(req.params.id).then(race => {
        if(race) {
            res.json(race);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validClass(req.body)) {
        races.create(req.body).then(races => {
            res.json(races[0]);
        });
    } else {
        next(new Error('Invalid race'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validClass(req.body)) {
        races.update(req.params.id, req.body).then(races => {
            res.json(races[0]);
        });
    } else {
        next(new Error('Invalid race'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    races.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;