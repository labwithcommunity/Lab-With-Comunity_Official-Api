package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.GetLoggedUserDto;
import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    public UserResponseDto findUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    public UserCreateResponseDto registerUser(UserCreateDto userCreateDto) {
        return userService.register(userCreateDto);
    }

    public GetLoggedUserDto getLoggedUser(String username) {
        return userService.getLoggedUser(username);
    }
//    public boolean addRolesToUser(Set<UserMemberRoles> userMemberRoles, String username){
//        return userService.addRoleToUser(userMemberRoles, username);
//    }
}
