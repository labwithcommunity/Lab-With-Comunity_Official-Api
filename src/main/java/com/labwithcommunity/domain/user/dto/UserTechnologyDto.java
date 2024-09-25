package com.labwithcommunity.domain.user.dto;

import com.labwithcommunity.domain.user.enums.ProgrammingLanguage;
import com.labwithcommunity.domain.user.enums.TechnologiesForProgrammingLanguage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserTechnologyDto {
    private ProgrammingLanguage programmingLanguage;
    private Set<TechnologiesForProgrammingLanguage> userTechnologyForProgrammingLanguages;
}