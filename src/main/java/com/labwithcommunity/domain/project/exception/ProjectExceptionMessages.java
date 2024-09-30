package com.labwithcommunity.domain.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProjectExceptionMessages {

    NO_PROJECTS_FOUND_FOR_GIVEN_USER("no projects found for given user"),
    PROJECT_WITH_GIVEN_TITLE_NOT_FOUND("project with given title does not exist"),
    PROJECT_WITH_GIVEN_TITLE_ALREADY_EXISTS("project with given title already exists"),
    USER_ALREADY_SIGNED_TO_PROJECT("user already in project"),
    PROJECT_ID_NOT_FOUND("project id not found");
    private final String message;
}
