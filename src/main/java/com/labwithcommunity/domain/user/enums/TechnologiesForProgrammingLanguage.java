package com.labwithcommunity.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TechnologiesForProgrammingLanguage {
    BACKEND("Backend"),
    SPRING("Spring"),
    HIBERNATE("Hibernate");
    private final String name;
}
