package com.labwithcommunity.domain.user;


import com.labwithcommunity.domain.user.enums.ProgrammingLanguage;
import com.labwithcommunity.domain.user.enums.TechnologiesForProgrammingLanguage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class Technologies implements Serializable {

    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;
    private Set<TechnologiesForProgrammingLanguage> userTechnologyForProgrammingLanguages = new HashSet<>();
}

