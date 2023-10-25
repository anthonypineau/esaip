const express = require('express');

const medias = require('../db/medias');

const validHandler = require('../handler/valid');

const router = express.Router();

router.get('/', (req, res) => {
    medias.getAll().then(medias => {
        res.json(medias);
    });
});

router.get('/:id', validHandler.isValidId, (req, res, next) => {
    medias.getOne(req.params.id).then(media => {
        if(media) {
            res.json(media);
        } else {
            next();
        }
    });
});

router.post('/', (req, res, next) => {
    if(validHandler.validMedia(req.body)) {
        medias.create(req.body).then(medias => {
            res.json(medias[0]);
        });
    } else {
        next(new Error('Invalid media'));
    }
});

router.put('/:id', validHandler.isValidId, (req, res, next) => {
    if(validHandler.validMedia(req.body)) {
        medias.update(req.params.id, req.body).then(medias => {
            res.json(medias[0]);
        });
    } else {
        next(new Error('Invalid media'));
    }
});

router.delete('/:id', validHandler.isValidId, (req, res) => {
    medias.delete(req.params.id).then(() => {
        res.json({
            deleted: true
        });
    });
});

module.exports = router;