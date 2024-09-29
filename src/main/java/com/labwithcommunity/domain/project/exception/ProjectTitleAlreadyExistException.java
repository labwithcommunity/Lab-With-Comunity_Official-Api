package com.labwithcommunity.domain.project.exception;

public class ProjectTitleAlreadyExistException extends RuntimeException {
    public ProjectTitleAlreadyExistException(String message) {
        super(message);
    }
}
