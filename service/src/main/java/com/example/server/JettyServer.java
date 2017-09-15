package com.example.server;


import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * @author ziggzagg
 */
public class JettyServer {

    public static final int PORT = 9999;

    private void start() throws Exception {
        Server server = new Server(PORT);
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        server.setHandler(contextHandler);
        ServletHolder servletHolder = new ServletHolder(ServletContainer.class);
        servletHolder.setInitParameter(
                "com.sun.jersey.config.property.resourceConfigClass",
                "com.sun.jersey.api.core.PackagesResourceConfig"
        );

        servletHolder.setInitParameter(
                "com.sun.jersey.config.property.packages",
                "com.example.rest"
        );

        contextHandler.addServlet(servletHolder, "/*");

        server.start();
    }

    public void stop() throws Exception {}

    public static void main(String[] args) throws Exception {
        JettyServer server = new JettyServer();
        server.start();
    }
}
