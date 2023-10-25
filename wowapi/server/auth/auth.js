const express = require('express');
const jwt = require('jsonwebtoken');

const router = express.Router();

/*
app.use('/', isUserConnected){

}
*/

router.get('/', (req, res) => {
    const bool = 1;
    if(bool==2){
        next();
    }else{
        new Error("error");
    }
});

router.post('/login', (req, res) => {
    // Mock user
    const user = {
        id: 1,
        username: 'brad',
        email: 'brad@gmail.com'
    }

    jwt.sign({user}, 'secretkey', { expiresIn: '2h' }, (err, token) => {
        res.json({
            token
        });
    });
});

router.post('/api/posts', verifyToken, (req, res) => { 
console.log(req.token);
jwt.verify(req.token, 'secretkey', (err, authData) => {
if(err) {
res.sendStatus(403);
} else {
res.json({
message: 'Post created...',
authData
});
}
});
});

// FORMAT OF TOKEN
// Authorization: Bearer <access_token>

// Verify Token
function verifyToken(req, res, next) {
// Get auth header value
const bearerHeader = req.headers['authorization'];
// Check if bearer is undefined
if(typeof bearerHeader !== 'undefined') {
// Split at the space
const bearer = bearerHeader.split(' ');
// Get token from array
const bearerToken = bearer[1];
// Set the token
req.token = bearerToken;
// Next middleware
next();
} else {
// Forbidden
res.sendStatus(403);
}

}

module.exports = router;