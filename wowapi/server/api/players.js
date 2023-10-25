const express = require('express');

const players = require('../db/players');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    players.getAll().then(players => {
        res.json(players);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    players.getOne(req.params.id).then(player => {
        if(player) {
            res.json(player);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validPlayer(req.body)) {
        players.create(req.body).then(players => {
            res.json(players[0]);
        });
    } else {
        next(new Error('Invalid player'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validPlayer(req.body)) {
        players.update(req.params.id, req.body).then(players => {
            res.json(players[0]);
        });
    } else {
        next(new Error('Invalid player'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    players.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;