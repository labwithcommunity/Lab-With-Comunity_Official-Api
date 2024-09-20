package com.labwithcommunity.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProgrammingLanguage {
    JAVA("Java"), PYTHON("Python");
    private final String name;

}
