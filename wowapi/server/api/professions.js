const express = require('express');

const professions = require('../db/professions');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    professions.getAll().then(professions => {
        res.json(professions);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    professions.getOne(req.params.id).then(profession => {
        if(profession) {
            res.json(profession);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validProfession(req.body)) {
        professions.create(req.body).then(professions => {
            res.json(professions[0]);
        });
    } else {
        next(new Error('Invalid profession'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validProfession(req.body)) {
        professions.update(req.params.id, req.body).then(professions => {
            res.json(professions[0]);
        });
    } else {
        next(new Error('Invalid profession'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    professions.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;