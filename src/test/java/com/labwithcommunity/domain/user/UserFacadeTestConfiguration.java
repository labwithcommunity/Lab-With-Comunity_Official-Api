package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserTechnologyDto;
import com.labwithcommunity.domain.user.enums.ProgrammingLanguage;
import com.labwithcommunity.domain.user.enums.TechnologiesForProgrammingLanguage;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;

class UserFacadeTestConfiguration {

    InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
    UserCreateDto userRegisterDto;
    UserCreateDto userRegisterDtoWithTwoTechnologies;
    HashSet<UserTechnologyDto> setOfTechDto = new HashSet<>();
    HashSet<UserTechnologyDto> setOfTechDto2 = new HashSet<>();
    HashSet<TechnologiesForProgrammingLanguage> setOfTech = new HashSet<>();
    HashSet<TechnologiesForProgrammingLanguage> setOfTech2 = new HashSet<>();

    @BeforeEach
    void setUp() {
        setOfTech.add(TechnologiesForProgrammingLanguage.HIBERNATE);
        setOfTech2.add(TechnologiesForProgrammingLanguage.HIBERNATE);
        setOfTech2.add(TechnologiesForProgrammingLanguage.BACKEND);
//        UserTechnologyDto technology = new UserTechnologyDto(ProgrammingLanguage.JAVA, setOfTech);
//        UserTechnologyDto technology2 = new UserTechnologyDto(ProgrammingLanguage.JAVA, setOfTech2);
//        setOfTechDto.add(technology);
//        setOfTechDto2.add(technology2);
//        userRegisterDto = new UserCreateDto("userTest", "password", "emailTest",setOfTechDto);
//        userRegisterDtoWithTwoTechnologies = new UserCreateDto("userTest2", "password2", "emailTest",setOfTechDto2);
    }
}
