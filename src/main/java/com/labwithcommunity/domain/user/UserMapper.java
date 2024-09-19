package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;

class UserMapper {

    static UserEntity mapToUserEntity(UserCreateDto user) {
        return new UserEntity(user.nickname(),
                user.email(),
                user.password());
    }

    static UserResponseDto mapToUserResponseDto(UserEntity userEntity) {
        return new UserResponseDto(userEntity.getUsername(),
                userEntity.getNickname(),
                userEntity.getEmail(),
                userEntity.getRoles());
    }
}
