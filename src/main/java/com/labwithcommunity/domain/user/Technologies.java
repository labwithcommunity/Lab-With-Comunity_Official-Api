package com.labwithcommunity.domain.user;


import com.labwithcommunity.domain.user.enums.ProgrammingLanguage;
import com.labwithcommunity.domain.user.enums.TechnologiesForProgrammingLanguage;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
class Technologies {

    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;
    @Enumerated(EnumType.STRING)
    private Set<TechnologiesForProgrammingLanguage> userTechnologyForProgrammingLanguages = new HashSet<>();

    public void removeTechnology(Set<TechnologiesForProgrammingLanguage> technology) {
        userTechnologyForProgrammingLanguages.removeAll(technology);
    }

    public boolean isEmpty() {
        return userTechnologyForProgrammingLanguages.isEmpty();
    }
}

