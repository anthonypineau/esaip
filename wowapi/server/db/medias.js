const knex = require('./knex');

module.exports = {
    /*  select * from medias */
    getAll(){
        return knex("medias");
    },
    /*  select * from medias where id=? */
    getOne(id){
        return knex("medias").where('id', id);
    },
    /*  insert into medias values(...) */
    create(media) {
        return knex("medias").insert(media, '*');
    },
    /*  update medias set ... where id=? */
    update(id, media) {
        return knex("medias").where('id', id).update(media, '*');
    },
    /*  delete from medias where id=? */
    delete(id) {
        return knex("medias").where('id', id).del();
    }
}