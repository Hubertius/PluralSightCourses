package com.pluralsight.pluralsightcourses.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseRetriever {

    private static final Logger logger = LoggerFactory.getLogger(CourseRetriever.class);
    public static void main(String[] args) {
        logger.info("CourseRetriever starting...");
        if(args.length == 0) {
            logger.warn("Please provide author name as the first argument!");
            return;
        }

        retrieveCourses(args[0]);
    }

    private static void retrieveCourses(String authorId) {
        logger.info("Retrieving courses for author: " + authorId);
    }
}