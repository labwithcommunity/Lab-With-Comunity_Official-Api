package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.dto.UserTechnologyDto;
import com.labwithcommunity.domain.user.enums.ProgrammingLanguage;
import com.labwithcommunity.domain.user.enums.TechnologiesForProgrammingLanguage;
import com.labwithcommunity.domain.user.exception.UserAlreadyExistsException;
import com.labwithcommunity.domain.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    private final UserFacade userFacade = new UserFacade(new UserService(new InMemoryUserRepository(),new InMemoryPasswordEncoder()),
            new TechnologyRegistryService(new UserService(new InMemoryUserRepository(),new InMemoryPasswordEncoder())));
    private UserCreateDto userRegisterDto;

    @BeforeEach
    void setUp() {
        userRegisterDto = new UserCreateDto("userTest",
                "password","emailTest"
                , Set.of(new UserTechnologyDto(ProgrammingLanguage.JAVA,
                Set.of(TechnologiesForProgrammingLanguage.SPRING, TechnologiesForProgrammingLanguage.HIBERNATE))));
    }

    private UserCreateResponseDto registerTestUser() {
        return userFacade.registerUser(userRegisterDto);
    }

    @Test
    void shouldSaveUserSuccessfully() {
        //Given & When
        UserCreateResponseDto saveUser = registerTestUser();

        //Then
        assertAll(
                () -> Assertions.assertNotNull(saveUser),
                ()-> assertEquals(userRegisterDto.username(), saveUser.nickname()),
                ()-> assertEquals(userRegisterDto.username(), saveUser.username()),
                ()-> assertEquals(userRegisterDto.email(), saveUser.email())
        );
    }

    @Test
    void shouldThrowExceptionWhenTryToSaveWithNicknameAlreadySaved(){
        //Given
        UserCreateResponseDto userInDb = registerTestUser();

        //When && Then
        assertEquals(userRegisterDto.username(), userInDb.nickname());
        assertThrows(UserAlreadyExistsException.class,
                () -> userFacade.registerUser(userRegisterDto));
    }

//    @Test
//     void shouldAddMemberRoleToUserSuccessfully() {
//        //Given
//        Set<UserMemberRoles> role = Set.of(UserMemberRoles.MEMBER);
//        String nickname = userRegisterDto.username();
//        registerTestUser();
//
//        //When
//        userFacade.addRolesToUser(role,nickname);
//
//        //Then
//        UserResponseDto userByNickname = userFacade.findUserByUsername(nickname);
//        assertEquals(role, userByNickname.roles());
//    }

    @Test
     void shoudlFindUserWithGivenNicknameSuccessfully() {
        //Given
        String nickname = userRegisterDto.username();
        registerTestUser();

        //When
        UserResponseDto userByNickname = userFacade.findUserByUsername(nickname);

        //Then
        assertEquals(userRegisterDto.username(), userByNickname.nickname());
    }

    @Test
    void shouldThrowUserNotFoundException() {
        //Given
        registerTestUser();
        String correctNickname = userRegisterDto.username();

        //When && Then
        String wrongNickname = "wrongNickname";
        assertEquals(correctNickname, userRegisterDto.username());
        assertThrows(UserNotFoundException.class,
                () -> userFacade.findUserByUsername(wrongNickname));
    }
}
