const knex = require('./knex');

module.exports = {
    /*  select * from itemclasses */
    getAll(){
        return knex("itemclasses");
    },
    /*  select * from itemclasses where id=? */
    getOne(id){
        return knex("itemclasses").where('id', id);
    },
    /*  insert into itemclasses values(...) */
    create(itemClass) {
        return knex("itemclasses").insert(itemClass, '*');
    },
    /*  update itemclasses set ... where id=? */
    update(id, itemClass) {
        return knex("itemclasses").where('id', id).update(itemClass, '*');
    },
    /*  delete from itemclasses where id=? */
    delete(id) {
        return knex("itemclasses").where('id', id).del();
    }
}