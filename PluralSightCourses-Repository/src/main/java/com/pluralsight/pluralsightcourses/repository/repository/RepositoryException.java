package com.pluralsight.pluralsightcourses.repository.repository;

import java.sql.SQLException;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String exceptionMessage, SQLException exception) {
        super(exceptionMessage, exception);
    }
}
