package com.labwithcommunity.domain.project.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProjectExceptionMessages {


    NO_PROJECTS_FOUND_FOR_GIVEN_USER("no projects found for given user"),;
    private final String message;
}
