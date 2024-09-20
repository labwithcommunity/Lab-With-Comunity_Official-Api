package com.labwithcommunity.domain.user;


import com.labwithcommunity.domain.user.enums.ProgrammingLanguage;
import com.labwithcommunity.domain.user.enums.TechnologiesForProgrammingLanguage;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class Technologies  {

    private ProgrammingLanguage programmingLanguage;
    private Set<TechnologiesForProgrammingLanguage> userTechnologyForProgrammingLanguages;
}
