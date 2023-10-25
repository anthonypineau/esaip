const knex = require('./knex');

module.exports = {
    /*  select * from qualities */
    getAll(){
        return knex("qualities");
    },
    /*  select * from qualities where id=? */
    getOne(id){
        return knex("qualities").where('id', id);
    },
    /*  insert into qualities values(...) */
    create(quality) {
        return knex("qualities").insert(quality, '*');
    },
    /*  update qualities set ... where id=? */
    update(id, quality) {
        return knex("qualities").where('id', id).update(quality, '*');
    },
    /*  delete from classes where id=? */
    delete(id) {
        return knex("qualities").where('id', id).del();
    }
}