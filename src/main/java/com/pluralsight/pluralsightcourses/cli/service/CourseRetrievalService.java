package com.pluralsight.pluralsightcourses.cli.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CourseRetrievalService {
    private static final String PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";

    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public List<PluralsightCourse> getCoursesByAuthorId(String authorId) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(PS_URI.formatted(authorId)))
                .GET()
                .build();
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return switch(response.statusCode()) {
                case 200 -> getPluralsightCourses(response);
                case 404 -> List.of();
                default -> throw new RuntimeException("Pluralsight API called with status code: " + response.statusCode());
            };
        } catch(IOException | InterruptedException e) {
            throw new RuntimeException("Could not call PluralSight API. :(", e);
        }

    }

    private static List<PluralsightCourse> getPluralsightCourses(HttpResponse<String> response) throws JsonProcessingException {
        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, PluralsightCourse.class);
        return OBJECT_MAPPER.readValue(response.body(), javaType);
    }
}
