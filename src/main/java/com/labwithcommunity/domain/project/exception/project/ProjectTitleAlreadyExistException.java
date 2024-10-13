package com.labwithcommunity.domain.project.exception.project;

public class ProjectTitleAlreadyExistException extends RuntimeException {
    public ProjectTitleAlreadyExistException(String message) {
        super(message);
    }
}
