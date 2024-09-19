package com.pluralsight.pluralsightcourses.cli;

import com.pluralsight.pluralsightcourses.cli.service.CourseRetrievalService;
import com.pluralsight.pluralsightcourses.cli.service.CourseStorageService;
import com.pluralsight.pluralsightcourses.cli.service.PluralsightCourse;
import com.pluralsight.pluralsightcourses.repository.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.function.Predicate.not;

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
        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesByAuthorId(authorId).stream().filter(not(PluralsightCourse::isRetired)).toList();
        logger.info("Retrieved the following {} courses {}", coursesToStore.size(), coursesToStore);

        CourseRepository courseRepository = CourseRepository.openCourseRepository("./courses.db");
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);
        courseStorageService.storePluralSightCourses(coursesToStore);
        logger.info("Courses successfuly stored");
    }
}