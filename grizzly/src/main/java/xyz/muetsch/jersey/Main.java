package xyz.muetsch.jersey;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.http.server.ServerConfiguration;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.glassfish.grizzly.strategies.SameThreadIOStrategy;
import org.glassfish.grizzly.strategies.WorkerThreadIOStrategy;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpContainer;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ext.RuntimeDelegate;
import java.io.IOException;

public class Main {
    public static HttpServer startServer() {
        HttpServer server = new HttpServer();
        server.addListener(new NetworkListener("grizzly", "0.0.0.0", 8080));

        final TCPNIOTransportBuilder transportBuilder = TCPNIOTransportBuilder.newInstance();
        //transportBuilder.setIOStrategy(WorkerThreadIOStrategy.getInstance());
        transportBuilder.setIOStrategy(SameThreadIOStrategy.getInstance());
        server.getListener("grizzly").setTransport(transportBuilder.build());
        final ResourceConfig rc = new ResourceConfig().packages("xyz.muetsch.jersey");
        rc.register(JacksonFeature.class);
        final ServerConfiguration config = server.getServerConfiguration();
        config.addHttpHandler(RuntimeDelegate.getInstance().createEndpoint(rc, GrizzlyHttpContainer.class), "/rest/todo/");

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return server;
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started."));
        System.in.read();
        server.stop();
    }
}

