package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;

class UserMapper {

    static UserEntity mapToUserEntity(UserCreateDto userCreateDto, String username) {
        return UserEntity.builder().username(userCreateDto.email())
                .nickname(username)
                .password(userCreateDto.password())
                .isAdmin(false)
                .isApproved(false)
                .isActive(true)
                .email(userCreateDto.email())
                .build();
    }

    static UserQueryDto mapToQueryDto(UserEntity userEntity) {
        return new UserQueryDto(userEntity.getId(),
                userEntity.getNickname(),
                userEntity.getEmail());
    }

    static UserResponseDto mapToUserResponseDto(UserEntity userEntity) {
        return new UserResponseDto(userEntity.getUsername(),
                userEntity.getNickname(),
                userEntity.getEmail());
    }
}
