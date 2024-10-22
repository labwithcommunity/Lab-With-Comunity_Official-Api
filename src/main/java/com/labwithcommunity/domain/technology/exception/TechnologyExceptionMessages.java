package com.labwithcommunity.domain.technology.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechnologyExceptionMessages {

    TECHNOLOGY_NOT_FOUND("technology not found with given id"),
    TECHNOLOGY_ALREADY_EXIST("Technology already exists");
    private final String message;
}
