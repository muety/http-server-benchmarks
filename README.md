# http-server-benchmarks
This is a very basic benchmark suite to compare different HTTP server's performance. It is inspired by [arcadius/java-rest-api-web-container-benchmark](https://github.com/arcadius/java-rest-api-web-container-benchmark), but uses [h2load](https://github.com/nghttp2/nghttp2#benchmarking-tool) instead of [ab](http://httpd.apache.org/docs/2.4/programs/ab.html).

It works by running a predifined number of requests from a predefined number of concurrent clients against an HTTP server at localhost and measuring, among others, the execution time. 

Currently, I included server implementations in __Java__ (using [Jersey](http://jersey.java.net/) with an embedded [Grizzly](https://grizzly.java.net/) server), __Go__ (using `net/http`) and __NodeJS__ (once using plain `http` package and again using the de-facto standard [Express](http://expressjs.com/) framework). All implementations are very basic REST APIs consisting of exactly one route, which exposes a static set of simple resources (Todo Items) as JSON. My results can be found in the _results_ folder.

## How to run
### Requirements
* [h2load](https://github.com/nghttp2/nghttp2#benchmarking-tool) to be installed

### Running
1. Clone this repository
2. Start an HTTP server on your local machine
3. Adapt parameters in `run-load.sh`
    1. `URL`: The URL of your HTTP server's endpoint to be tested against
    2. `NUM_REQUESTS`: Total number of request to execute across all clients
    3. `CONCURRENCY`: Number of concurrent clients to perform requests
    4. `THREADS`: Number of CPU threads to use for h2load
4. `chmod +x run-load.sh`
5. `./run-load.sh` 
6. View `results.log`

## Java vs. Go vs. Node
Running this benchmark suite gave me the following results. To read more details about my test setup etc., please refer to [this article](https://medium.com/@n1try/http-performance-java-jersey-vs-go-vs-nodejs-1d86f646a03c#.pvw2u1i35).

![](https://cdn-images-1.medium.com/max/1200/1*lPQ_ZshX8ibYl0l0wsOTEw.png)

## License
MIT @ [Ferdinand Mütsch](https://ferdinand-muetsch.de)