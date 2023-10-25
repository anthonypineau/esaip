const knex = require('./knex');

module.exports = {
    /*  select * from factions */
    getAll(){
        return knex("factions");
    },
    /*  select * from factions where id=? */
    getOne(id){
        return knex("factions").where('id', id);
    },
    /*  insert into factions values(...) */
    create(faction) {
        return knex("factions").insert(faction, '*');
    },
    /*  update factions set ... where id=? */
    update(id, faction) {
        return knex("factions").where('id', id).update(faction, '*');
    },
    /*  delete from factions where id=? */
    delete(id) {
        return knex("factions").where('id', id).del();
    }
}