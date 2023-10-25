const express = require('express');

const factions = require('../db/factions');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    factions.getAll().then(factions => {
        res.json(factions);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    factions.getOne(req.params.id).then(faction => {
        if(faction) {
            res.json(faction);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validFaction(req.body)) {
        factions.create(req.body).then(factions => {
            res.json(factions[0]);
        });
    } else {
        next(new Error('Invalid faction'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validFaction(req.body)) {
        factions.update(req.params.id, req.body).then(factions => {
            res.json(factions[0]);
        });
    } else {
        next(new Error('Invalid faction'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    factions.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;