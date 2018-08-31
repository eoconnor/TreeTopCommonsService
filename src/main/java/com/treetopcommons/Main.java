package com.treetopcommons;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 *
 */
public class Main {

    public static void main(String[] args) {

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        contextHandler.setContextPath("/");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        ServletHolder servletHolder = contextHandler.addServlet(ServletContainer.class, "/treetop/*");
        servletHolder.setInitOrder(1);
        servletHolder.setInitParameter("jersey.config.server.provider.packages",
                "com.treetopcommons.resources");

        try {
            Logger.getLogger(Main.class.getName()).log(Level.INFO, "Starting server, listening on port 8080");
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            server.destroy();
        }
    }
}
