package com.pluralsight.pluralsightcourses.cli.service;

import com.pluralsight.pluralsightcourses.repository.domain.Course;
import com.pluralsight.pluralsightcourses.repository.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseStorageService {

    private static final String PS_BASE_URL = "https://app.pluralsight.com";
    private final CourseRepository courseRepository;

    public CourseStorageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void storePluralSightCourses(List<PluralsightCourse> pluralsightCourses) {
        for(var psCourse: pluralsightCourses) {
            Course course = new Course(psCourse.id(),
                    psCourse.title(), psCourse.durationInMinutes(), PS_BASE_URL + psCourse.contentUrl(), Optional.empty());
            courseRepository.saveCourse(course);
        }
    }
}
