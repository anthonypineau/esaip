const knex = require('./knex');

module.exports = {
    /* select username, count(*) as number-OfItems from players inner join play-ersitems on players.id= playersi-tems.player group by players.id, username */
    getPlayerWhoHasMostItemInInventory(){
        return knex.select('username').count('*', {as:'numberOfItems'})
            .from('players').innerJoin('playersitems', 'players.id', 'playersitems.player').groupBy('players.id','username');
    },
    /* select name, purchasePrice-sellPrice as difference from items limit 5 */
    getDifferenceBetweenSellPriceAndPurchasePrice(){
        return knex.raw("select name, purchasePrice-sellPrice as difference from items limit 5");
    },
    /*  */
    getNumberOfMountsByPlayer(){
        return knex.select('username').count('*', {as:'numberOfMounts'})
            .from('players').innerJoin('mounts', 'players.id', 'mounts.player').groupBy('players.id','username').orderBy('numberOfMounts', 'desc');
    }
}