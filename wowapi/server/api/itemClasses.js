const express = require('express');

const itemClasses = require('../db/itemClasses');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    itemClasses.getAll().then(itemClasses => {
        res.json(itemClasses);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    itemClasses.getOne(req.params.id).then(itemClass => {
        if(itemClass) {
            res.json(itemClass);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validItemClass(req.body)) {
        itemClasses.create(req.body).then(itemClasses => {
            res.json(itemClasses[0]);
        });
    } else {
        next(new Error('Invalid item class'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validItemClass(req.body)) {
        itemClasses.update(req.params.id, req.body).then(itemClasses => {
            res.json(itemClasses[0]);
        });
    } else {
        next(new Error('Invalid item class'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    itemClasses.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;