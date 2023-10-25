const knex = require('./knex');

module.exports = {
    /*  select * from playersitems */
    getAll(){
        return knex("playersitems");
    },
    /*  select * from playersitems where id=? */
    getOne(id){
        return knex("playersitems").where('id', id);
    },
    /*  insert into playersitems values(...) */
    create(playerItems) {
        return knex("playersitems").insert(playerItems, '*');
    },
    /*  update playersitems set ... where id=? */
    update(id, playerItems) {
        return knex("playersitems").where('id', id).update(playerItems, '*');
    },
    /*  delete from playersitems where id=? */
    delete(id) {
        return knex("playersitems").where('id', id).del();
    }
}