package com.pluralsight.pluralsightcourses.repository.repository;

import com.pluralsight.pluralsightcourses.repository.domain.Course;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseJdbcRepository implements CourseRepository {

    private static final String H2_CONNECTION_URL = "jdbc:h2:file:%s:AUTO_SERVER=true;INIT=RUNSCRIPT FROM './db_init.sql'";

    private static final String INSERT_COURSE = """
            INSERT INTO Courses (id, name, length, url)
            VALUES (?, ?, ?, ?)
            """;
    private final DataSource dataSource;

    public CourseJdbcRepository(String databaseFile) {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(H2_CONNECTION_URL.formatted(databaseFile));
        this.dataSource = jdbcDataSource;
    }

    @Override
    public void saveCourse(Course course) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE);
            preparedStatement.setString(1, course.id());
            preparedStatement.setString(2, course.name());
            preparedStatement.setLong(3, course.length());
            preparedStatement.setString(4, course.url());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RepositoryException("Failed to save course: " + course, e);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");
            List<Course> courses = new ArrayList<>();
            while(resultSet.next()) {
                Course course = new Course(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getString(4)
                );
                courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            throw new RepositoryException("Failed to list courses!", e);
        }
    }

}
