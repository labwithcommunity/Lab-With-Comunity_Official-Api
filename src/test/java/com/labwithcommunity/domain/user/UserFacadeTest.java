package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.enums.UserMemberRoles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;

 class UserFacadeTest {

    private UserFacade userFacade = new UserFacade(new UserService(new InMemoryUserRepository()));

    private UserCreateDto userRegisterDto;

    @BeforeEach
    void setUp() {
        userRegisterDto = new UserCreateDto("userTest","password","emailTest",new HashSet<>());
    }

    @Test
    void shouldSaveUserSuccessfully() {
        //Given & When
        UserCreateResponseDto saveUser = userFacade.registerUser(userRegisterDto);

        //Then
        assertAll(
                () -> Assertions.assertNotNull(saveUser),
                ()-> Assertions.assertEquals(userRegisterDto.nickname(), saveUser.nickname()),
                ()-> Assertions.assertEquals(userRegisterDto.nickname(), saveUser.username()),
                ()-> Assertions.assertEquals(userRegisterDto.email(), saveUser.email())
        );
    }

    @Test
     void shouldAddMemberRoleToUserSuccessfully() {
        //Given
        Set<UserMemberRoles> role = Set.of(UserMemberRoles.MEMBER);
        String nickname = userRegisterDto.nickname();
         userFacade.registerUser(userRegisterDto);

        //When
        userFacade.addRolesToUser(role,nickname);

        //Then
        UserResponseDto userByNickname = userFacade.findUserByNickname(nickname);
        Assertions.assertEquals(role, userByNickname.roles());
    }
}
