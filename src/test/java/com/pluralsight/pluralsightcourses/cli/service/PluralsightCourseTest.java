package com.pluralsight.pluralsightcourses.cli.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PluralsightCourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            01:08:54.94444, 68
            00:05:37, 5
            00:00:00, 0
            """)
    void durationInMinutes(String inputTime, long expectedMinutes) {
        PluralsightCourse pluralsightCourse = new PluralsightCourse("id", "Test course", "00:05:37", "url", false);
        assertEquals(5, pluralsightCourse.durationInMinutes());
    }

}