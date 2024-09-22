package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.enums.UserMemberRoles;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public UserResponseDto findUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    public UserCreateResponseDto registerUser(UserCreateDto userCreateDto) {
        return userService.register(userCreateDto);
    }
//    public boolean addRolesToUser(Set<UserMemberRoles> userMemberRoles, String username){
//        return userService.addRoleToUser(userMemberRoles, username);
//    }
}
