package com.labwithcommunity.domain.project.exception.licence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LicenceExceptionMessages {

    LICENCE_NOT_FOUND_BY_GIVEN_ID("Licence not found with given id"),
    LICENCE_NAME_ALREADY_EXISTS("Licence name already exists"),
    LICENCE_SYMBOL_ALREADY_EXISTS("Licence symbol already exists"),
    LICENCE_ALREADY_EXISTS("Licence already exists");
    private final String message;

}
