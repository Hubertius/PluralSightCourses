package com.pluralsight.pluralsightcourses.server;

import com.pluralsight.pluralsightcourses.repository.domain.Course;
import com.pluralsight.pluralsightcourses.repository.repository.CourseRepository;
import com.pluralsight.pluralsightcourses.repository.repository.RepositoryException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;

@Path("/courses")
public class CourseResource {

    private static final Logger logger = LoggerFactory.getLogger(CourseResource.class);

    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses() {
        try {
            return courseRepository.getAllCourses().stream().sorted(Comparator.comparing(Course::id)).toList();
        } catch (RepositoryException e) {
            throw new NotFoundException("Not found records within the database!");
        }
    }

    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNotes(@PathParam("id") String id, String notes) {
        courseRepository.addNotes(id, notes);
    }


}
