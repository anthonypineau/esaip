const express = require('express');

const playersItems = require('../db/playersItems');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    playersItems.getAll().then(playersItems => {
        res.json(playersItems);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    playersItems.getOne(req.params.id).then(playersItem => {
        if(playersItem) {
            res.json(playersItem);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validPlayerItem(req.body)) {
        playersItems.create(req.body).then(playersItems => {
            res.json(playersItems[0]);
        });
    } else {
        next(new Error('Invalid playersItem'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validPlayerItem(req.body)) {
        playersItems.update(req.params.id, req.body).then(playersItems => {
            res.json(playersItems[0]);
        });
    } else {
        next(new Error('Invalid playersItem'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    playersItems.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;