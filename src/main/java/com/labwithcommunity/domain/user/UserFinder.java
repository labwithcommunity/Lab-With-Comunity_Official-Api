package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.GetLoggedUserDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;

interface UserFinder {

    UserResponseDto findUserByUsername(String username);
    GetLoggedUserDto getLoggedUser(String username);
    UserEntity getUserEntity(String username);
    UserQueryDto getUserQuery(String username);
}
