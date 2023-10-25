const knex = require('./knex');

module.exports = {
    /*  select * from powertypes */
    getAll(){
        return knex("powertypes");
    },
    /*  select * from powertypes where id=? */
    getOne(id){
        return knex("powertypes").where('id', id);
    },
    /*  insert into powertypes values(...) */
    create(powertype) {
        return knex("powertypes").insert(powertype, '*');
    },
    /*  update powertypes set ... where id=? */
    update(id, powertype) {
        return knex("powertypes").where('id', id).update(powertype, '*');
    },
    /*  delete from powertypes where id=? */
    delete(id) {
        return knex("powertypes").where('id', id).del();
    }
}