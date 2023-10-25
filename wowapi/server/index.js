require('dotenv').config()

const express = require('express');
const app = express();
const bodyParser = require('body-parser');
const volleyball = require('volleyball');

const handler = require('./handler/handler')
const auth = require('./auth/auth')

const users = require('./api/users');
const classes = require('./api/classes');
const itemClasses = require('./api/itemClasses');
const items = require('./api/items');
const mounts = require('./api/mounts');
const players = require('./api/players');
const playersItems = require('./api/playersItems');
const professions = require('./api/professions');
const races = require('./api/races');
const statistics = require('./api/statistics');
const medias = require('./api/medias');
const sources = require('./api/sources');
const factions = require('./api/factions');
const powerTypes = require('./api/powerTypes');
const types = require('./api/types');
const qualities = require('./api/qualities');
const { func } = require('joi');

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.use(volleyball);

app.get('/', (req, res) => {
    //res.sendStatus(403);
    res.json(
        {
            "message": "home page"
        }
    );
});

function authHandler(req, res, next){
    const a=1;
    console.log(req.headers);
    if(a==1){
        next();
    }else{
        res.sendStatus(403);
    }
}

function corsPolicy(req,res,next){
    res.set({"Access-Control-Allow-Origin" : "*", 
        "Access-Control-Allow-Credentials" : true,
        "Access-Control-Allow-Methods": "*",
        "Access-Control-Allow-Headers": "*" });
        next();
}

app.use(authHandler);
app.use(corsPolicy);

app.use('/classes', classes);
app.use('/itemClasses', itemClasses);
app.use('/items', items);
app.use('/mounts', mounts);
app.use('/players', players);
app.use('/playersItems', playersItems);
app.use('/professions', professions);
app.use('/races', races);
app.use('/users', users);
app.use('/statistics', statistics);
app.use('/medias', medias);
app.use('/sources', sources);
app.use('/factions', factions);
app.use('/powerTypes', powerTypes);
app.use('/types', types);
app.use('/qualities', qualities);

app.use(handler.notFound);
app.use(handler.errorHandler);

app.listen(process.env.PORT, () => {
    console.log(`Example app listening at http://localhost:3000`);
})