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
    HashSet<UserTechnologyDto> setOfTechDto = new HashSet<>();
    HashSet<TechnologiesForProgrammingLanguage> setOfTech = new HashSet<>();

    @BeforeEach
    void setUp() {
        setOfTech.add(TechnologiesForProgrammingLanguage.HIBERNATE);
        UserTechnologyDto technology = new UserTechnologyDto(ProgrammingLanguage.JAVA, setOfTech);
        setOfTechDto.add(technology);
        userRegisterDto = new UserCreateDto("userTest",
                "password", "emailTest"
                , setOfTechDto);
    }
}
