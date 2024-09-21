package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.enums.UserMemberRoles;
import com.labwithcommunity.domain.user.exception.UserAlreadyExistsException;
import com.labwithcommunity.domain.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    private UserFacade userFacade = new UserFacade(new UserService(new InMemoryUserRepository()));
    private UserCreateDto userRegisterDto;

    @BeforeEach
    void setUp() {
        userRegisterDto = new UserCreateDto("userTest","password","emailTest",new HashSet<>());
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
                ()-> assertEquals(userRegisterDto.nickname(), saveUser.nickname()),
                ()-> assertEquals(userRegisterDto.nickname(), saveUser.username()),
                ()-> assertEquals(userRegisterDto.email(), saveUser.email())
        );
    }

    @Test
    void shouldThrowExceptionWhenTryToSaveWithNicknameAlreadySaved(){
        //Given
        UserCreateResponseDto userInDb = registerTestUser();

        //When && Then
        assertEquals(userRegisterDto.nickname(), userInDb.nickname());
        assertThrows(UserAlreadyExistsException.class,
                () -> userFacade.registerUser(userRegisterDto));
    }

    @Test
     void shouldAddMemberRoleToUserSuccessfully() {
        //Given
        Set<UserMemberRoles> role = Set.of(UserMemberRoles.MEMBER);
        String nickname = userRegisterDto.nickname();
        registerTestUser();

        //When
        userFacade.addRolesToUser(role,nickname);

        //Then
        UserResponseDto userByNickname = userFacade.findUserByNickname(nickname);
        assertEquals(role, userByNickname.roles());
    }

    @Test
     void shoudlFindUserWithGivenNicknameSuccessfully() {
        //Given
        String nickname = userRegisterDto.nickname();
        registerTestUser();

        //When
        UserResponseDto userByNickname = userFacade.findUserByNickname(nickname);

        //Then
        assertEquals(userRegisterDto.nickname(), userByNickname.nickname());
    }

    @Test
    void shouldThrowUserNotFoundException() {
        //Given
        registerTestUser();
        String correctNickname = userRegisterDto.nickname();

        //When && Then
        String wrongNickname = "wrongNickname";
        assertEquals(correctNickname, userRegisterDto.nickname());
        assertThrows(UserNotFoundException.class,
                () -> userFacade.findUserByNickname(wrongNickname));
    }
}
