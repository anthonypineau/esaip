const express = require('express');

const qualities = require('../db/qualities');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    qualities.getAll().then(qualities => {
        res.json(qualities);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    qualities.getOne(req.params.id).then(quality => {
        if(quality) {
            res.json(quality);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validQuality(req.body)) {
        qualities.create(req.body).then(qualities => {
            res.json(qualities[0]);
        });
    } else {
        next(new Error('Invalid quality'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validQuality(req.body)) {
        qualities.update(req.params.id, req.body).then(qualities => {
            res.json(qualities[0]);
        });
    } else {
        next(new Error('Invalid quality'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    qualities.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;