package com.labwithcommunity.domain.project.exception.methodology;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MethodologyExceptionMessages {

    METHODOLOGY_NOT_FOUND("methodology not found");
    private final String message;
}
