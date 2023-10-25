const knex = require('./knex');

module.exports = {
    /*  select * from types */
    getAll(){
        return knex("types");
    },
    /*  select * from types where id=? */
    getOne(id){
        return knex("types").where('id', id);
    },
    /*  insert into types values(...) */
    create(type) {
        return knex("types").insert(type, '*');
    },
    /*  update types set ... where id=? */
    update(id, type) {
        return knex("types").where('id', id).update(type, '*');
    },
    /*  delete from types where id=? */
    delete(id) {
        return knex("types").where('id', id).del();
    }
}