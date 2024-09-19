package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.enums.UserRoles;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public UserResponseDto findUserByNickname(String nickname) {
        return userService.getUserByNickname(nickname);
    }

    public UserCreateResponseDto registerUser(UserCreateDto userCreateDto) {
        return userService.register(userCreateDto);
    }
    public boolean addRolesToUser(Set<UserRoles> userRoles, String nickname){
        return userService.addRoleToUser(userRoles, nickname);
    }
}
