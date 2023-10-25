const express = require('express');

const items = require('../db/items');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    items.getAll().then(items => {
        res.json(items);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    items.getOne(req.params.id).then(item => {
        if(item) {
            res.json(item);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validItem(req.body)) {
        items.create(req.body).then(items => {
            res.json(items[0]);
        });
    } else {
        next(new Error('Invalid item'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validItem(req.body)) {
        items.update(req.params.id, req.body).then(items => {
            res.json(items[0]);
        });
    } else {
        next(new Error('Invalid item'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    items.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;