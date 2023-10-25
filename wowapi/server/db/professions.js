const knex = require('./knex');

module.exports = {
    /*  select * from professions */
    getAll(){
        return knex("professions");
    },
    /*  select * from professions where id=? */
    getOne(id){
        return knex("professions").where('id', id);
    },
    /*  insert into professions values(...) */
    create(profession) {
        return knex("professions").insert(profession, '*');
    },
    /*  update professions set ... where id=? */
    update(id, profession) {
        return knex("professions").where('id', id).update(profession, '*');
    },
    /*  delete from professions where id=? */
    delete(id) {
        return knex("professions").where('id', id).del();
    }
}