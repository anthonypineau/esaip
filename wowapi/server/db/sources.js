const knex = require('./knex');

module.exports = {
    /*  select * from sources */
    getAll(){
        return knex("sources");
    },
    /*  select * from sources where id=? */
    getOne(id){
        return knex("sources").where('id', id);
    },
    /*  insert into sources values(...) */
    create(source) {
        return knex("sources").insert(source, '*');
    },
    /*  update races set ... where id=? */
    update(id, source) {
        return knex("sources").where('id', id).update(source, '*');
    },
    /*  delete from races where id=? */
    delete(id) {
        return knex("sources").where('id', id).del();
    }
}