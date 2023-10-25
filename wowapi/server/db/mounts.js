const knex = require('./knex');

module.exports = {
    /*  select * from mounts */
    getAll(){
        return knex("mounts");
    },
    /*  select * from mounts where id=? */
    getOne(id){
        return knex("mounts").where('id', id);
    },
    /*  insert into mounts values(...) */
    create(mount) {
        return knex("mounts").insert(mount, '*');
    },
    /*  update mounts set ... where id=? */
    update(id, mount) {
        return knex("mounts").where('id', id).update(mount, '*');
    },
    /*  delete from mounts where id=? */
    delete(id) {
        return knex("mounts").where('id', id).del();
    }
}