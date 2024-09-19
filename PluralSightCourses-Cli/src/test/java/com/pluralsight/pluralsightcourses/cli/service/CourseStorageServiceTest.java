package com.pluralsight.pluralsightcourses.cli.service;

import com.pluralsight.pluralsightcourses.repository.domain.Course;
import com.pluralsight.pluralsightcourses.repository.repository.CourseRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseStorageServiceTest {

    @Test
    void storePluralSightCourses() {
        CourseRepository courseRepository = new InMemoryRepositoryClass();
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);

        PluralsightCourse pluralsightCourse = new PluralsightCourse("1", "Title-1",
                "01:40:00.123", "/url-1", false);
        courseStorageService.storePluralSightCourses(List.of(pluralsightCourse));

        Course expected = new Course("1", "Title-1", 100, "https://app.pluralsight.com/url-1");
        assertEquals(courseRepository.getAllCourses().get(0), expected);
    }

    static class InMemoryRepositoryClass implements CourseRepository {

        private final List<Course> courses = new ArrayList<>();

        @Override
        public void saveCourse(Course course) {
            courses.add(course);
        }

        @Override
        public List<Course> getAllCourses() {
            return courses;
        }
    }
}