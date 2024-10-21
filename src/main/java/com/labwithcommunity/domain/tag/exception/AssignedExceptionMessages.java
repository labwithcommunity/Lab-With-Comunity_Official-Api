package com.labwithcommunity.domain.tag.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssignedExceptionMessages {

    ALREADY_ASSIGNED_TO_PROJECT("already assigned to project");

    private final String message;
}
