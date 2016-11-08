var express = require('express');
var app = express();

app.use(express.static('.'));

var server = app.listen(3000, function () {
    console.log('Dev server listening at http://%s:%s/', server.address().address, server.address().port);
});