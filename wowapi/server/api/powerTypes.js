const express = require('express');

const powerTypes = require('../db/powerTypes');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    powerTypes.getAll().then(powerTypes => {
        res.json(powerTypes);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    powerTypes.getOne(req.params.id).then(powerType => {
        if(powerType) {
            res.json(powerType);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validPowerType(req.body)) {
        powerTypes.create(req.body).then(powerTypes => {
            res.json(powerTypes[0]);
        });
    } else {
        next(new Error('Invalid powerType'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validPowerType(req.body)) {
        powerTypes.update(req.params.id, req.body).then(powerTypes => {
            res.json(powerTypes[0]);
        });
    } else {
        next(new Error('Invalid powerType'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    powerTypes.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;