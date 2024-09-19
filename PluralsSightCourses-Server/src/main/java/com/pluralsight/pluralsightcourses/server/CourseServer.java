package com.pluralsight.pluralsightcourses.server;

import com.pluralsight.pluralsightcourses.repository.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.net.URI;
import java.util.logging.LogManager;

public class CourseServer {

    static {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }
    private static final Logger logger = LoggerFactory.getLogger(CourseResource.class);
    private static final String BASE_URI = "http://localhost:8080";

    public static void main(String... args) {
        logger.info("Starting the server...");
        CourseRepository courseRepository = CourseRepository.openCourseRepository("./courses.db");
        ResourceConfig resourceConfig = new ResourceConfig().register(new CourseResource(courseRepository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
    }

}
