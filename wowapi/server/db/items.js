const knex = require('./knex');

module.exports = {
    /*  select * from items */
    getAll(){
        return knex("items");
    },
    /*  select * from items where id=? */
    getOne(id){
        return knex("items").where('id', id);
    },
    /*  insert into items values(...) */
    create(item) {
        return knex("items").insert(item, '*');
    },
    /*  update items set ... where id=? */
    update(id, item) {
        return knex("items").where('id', id).update(item, '*');
    },
    /*  delete from items where id=? */
    delete(id) {
        return knex("items").where('id', id).del();
    }
}