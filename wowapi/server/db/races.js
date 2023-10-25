const knex = require('./knex');

module.exports = {
    /*  select * from races */
    getAll(){
        return knex("races");
    },
    /*  select * from races where id=? */
    getOne(id){
        return knex("races").where('id', id);
    },
    /*  insert into races values(...) */
    create(race) {
        return knex("races").insert(race, '*');
    },
    /*  update races set ... where id=? */
    update(id, race) {
        return knex("races").where('id', id).update(race, '*');
    },
    /*  delete from races where id=? */
    delete(id) {
        return knex("races").where('id', id).del();
    }
}