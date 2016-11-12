var express = require('express');
var app = express();

app.use(express.static('src/main/webapp'));

app.use('/tronlikesx-fastopt.js', express.static('target/scala-2.11/tronlikesx-fastopt.js'));
app.use('/tronlikesx-fastopt.js.map', express.static('target/scala-2.11/tronlikesx-fastopt.js.map'));

var server = app.listen(3000, function () {
    console.log('Dev server listening at http://%s:%s/', server.address().address, server.address().port);
});