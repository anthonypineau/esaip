const express = require('express');

const statistics = require('../db/statistics');

const router = express.Router();

router.get('/', (req, res) => {
    statistics.getPlayerWhoHasMostItemInInventory().then(players => {
        res.json(players[0]);
    });
});

router.get('/playerMostItemInInventory', (req, res) => {
    statistics.getPlayerWhoHasMostItemInInventory().then(players => {
        res.json(players[0]);
    });
});

router.get('/differenceBetweenSellPriceAndPurchasePriceItem', (req, res) => {
    statistics.getDifferenceBetweenSellPriceAndPurchasePrice().then(items => {
        items = items[0];
        res.json(items);
    });
});

router.get('/numberOfMountsByPlayer', (req, res) => {
    statistics.getNumberOfMountsByPlayer().then(players => {
        res.json(players);
    });
});

module.exports = router;