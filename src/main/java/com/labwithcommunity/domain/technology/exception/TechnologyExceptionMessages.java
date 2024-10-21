package com.labwithcommunity.domain.technology.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechnologyExceptionMessages {

    TECHNOLOGY_ALREADY_EXIST("Technology already exists");
    private final String message;
}
