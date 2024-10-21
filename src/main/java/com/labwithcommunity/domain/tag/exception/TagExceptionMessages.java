package com.labwithcommunity.domain.tag.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TagExceptionMessages {

    TAG_NOT_FOUND("Tag not found"),
    TAG_WITH_GIVEN_NAME_ALREADY_EXIST("tag with given name already registered");
    private final String message;
}
