const express = require('express');

const sources = require('../db/sources');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    sources.getAll().then(sources => {
        res.json(sources);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    sources.getOne(req.params.id).then(source => {
        if(source) {
            res.json(source);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validSource(req.body)) {
        sources.create(req.body).then(sources => {
            res.json(sources[0]);
        });
    } else {
        next(new Error('Invalid source'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validSource(req.body)) {
        sources.update(req.params.id, req.body).then(sources => {
            res.json(sources[0]);
        });
    } else {
        next(new Error('Invalid source'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    sources.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;