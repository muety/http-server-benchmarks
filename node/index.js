const http = require('http')
    , cluster = require('cluster');

const todos = [
    { title: "Do some stuff", createdAt: new Date() },
    { title: "Do more stuff", createdAt: new Date() },
    { title: "Even more stuff", createdAt: new Date() },
    { title: "Yihaa", createdAt: new Date() },
    { title: "Java is great! Or isn't it?", createdAt: new Date() },
    { title: "Foo Bar", createdAt: new Date() },
    { title: "Lorem ipsum", createdAt: new Date() },
    { title: "Dolor sit amet", createdAt: new Date() }
];

if (cluster.isMaster) {
    let cpuCount = require('os').cpus().length;
    for (let i = 0; i < cpuCount; i += 1) cluster.fork();
}
else {
    http.createServer((req, res) => {
        switch (req.url) {
            case '/rest/todo':
                res.setHeader('content-type', 'application/json');
                res.end(JSON.stringify(todos));
                break;
        }
    }).listen(8080);
}