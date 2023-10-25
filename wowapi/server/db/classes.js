const knex = require('./knex');

module.exports = {
    /*  select * from classes */
    getAll(){
        return knex("classes");
    },
    /*  select * from classes where id=? */
    getOne(id){
        return knex("classes").where('id', id);
    },
    /*  insert into classes values(...) */
    create(classe) {
        return knex("classes").insert(classe, '*');
    },
    /*  update classes set ... where id=? */
    update(id, classe) {
        return knex("classes").where('id', id).update(classe, '*');
    },
    /*  delete from classes where id=? */
    delete(id) {
        return knex("classes").where('id', id).del();
    }
}