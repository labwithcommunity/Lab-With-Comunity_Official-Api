package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;

interface UserRegistration {

    UserCreateResponseDto register(UserCreateDto userCreateDto);
    boolean isUsernameExist(String username);
}
