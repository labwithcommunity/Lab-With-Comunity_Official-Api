package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.GetLoggedUserDto;
import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserFacade {

    private final UserRegistration userRegistration;
    private final UserFinder userFinder;

    public UserResponseDto findUserByUsername(String username) {
        return userFinder.findUserByUsername(username);
    }

    public UserCreateResponseDto registerUser(UserCreateDto userCreateDto) {
        return userRegistration.register(userCreateDto);
    }

    public GetLoggedUserDto getLoggedUser(String username) {
        return userFinder.getLoggedUser(username);
    }

    public UserQueryDto getQueryUser(String username) {
        return userFinder.getUserQuery(username);
    }

    public String approveRegisterEmail(String token) {
        return userRegistration.approveRegisterEmail(token);
    }
}
