const knex = require('./knex');

module.exports = {
    /*  select * from players */
    getAll(){
        return knex("players");
    },
    /*  select * from players where id=? */
    getOne(id){
        return knex("players").where('id', id);
    },
    /*  insert into players values(...) */
    create(player) {
        return knex("players").insert(player, '*');
    },
    /*  update players set ... where id=? */
    update(id, player) {
        return knex("players").where('id', id).update(player, '*');
    },
    /*  delete from players where id=? */
    delete(id) {
        return knex("players").where('id', id).del();
    }
}