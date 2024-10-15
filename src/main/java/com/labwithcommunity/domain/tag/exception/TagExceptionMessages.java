package com.labwithcommunity.domain.tag.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TagExceptionMessages {

    TAG_WITH_GIVEN_NAME_ALREADY_EXIST("tag with given name already registered");
    private final String message;
}
