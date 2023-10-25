const express = require('express');

const mounts = require('../db/mounts');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    mounts.getAll().then(mounts => {
        res.json(mounts);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    mounts.getOne(req.params.id).then(mount => {
        if(mount) {
            res.json(mount);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validMount(req.body)) {
        mounts.create(req.body).then(mounts => {
            res.json(mounts[0]);
        });
    } else {
        next(new Error('Invalid mount'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validMount(req.body)) {
        mounts.update(req.params.id, req.body).then(mounts => {
            res.json(mounts[0]);
        });
    } else {
        next(new Error('Invalid mount'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    mounts.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;