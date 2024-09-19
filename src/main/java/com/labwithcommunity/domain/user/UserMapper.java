package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;

class UserMapper {

    static UserEntity mapToUserEntity(UserCreateDto user) {
        return new UserEntity(user.nickname(),
                user.nickname(),
                user.email(),
                user.password());
    }
}
