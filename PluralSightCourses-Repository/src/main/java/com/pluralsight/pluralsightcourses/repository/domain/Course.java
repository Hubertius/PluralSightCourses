package com.pluralsight.pluralsightcourses.repository.domain;

public record Course(String id, String name, long length, String url) {

    public Course {
        filled(id);
        filled(name);
        filled(url);
    }

    private static void filled(String s) {
        if(s == null || s.isBlank()) {
            throw new IllegalArgumentException("Invalid argument passed into constructor of 'Course'!");
        }
    }
}
