package xyz.muetsch.jersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import xyz.muetsch.jersey.model.TodoItem;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/rest/";
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("xyz.muetsch.jersey");
        rc.register(JacksonFeature.class);

        // JDK HTTP SERVER
        // HttpServer server = JdkHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        // GRIZZLY SERVER (in a very simple benchmark, Grizzly was 200 times faster)
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        return server;
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

