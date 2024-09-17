package com.pluralsight.pluralsightcourses.cli;

import com.pluralsight.pluralsightcourses.cli.service.CourseRetrievalService;
import com.pluralsight.pluralsightcourses.cli.service.PluralsightCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();
        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesByAuthorId(authorId);
        logger.info("Retrieved the following {} courses {}", coursesToStore.size(), coursesToStore);
    }
}