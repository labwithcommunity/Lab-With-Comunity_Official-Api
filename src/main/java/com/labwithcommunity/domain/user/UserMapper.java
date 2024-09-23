package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.GetLoggedUserDto;
import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;

class UserMapper {

    static UserEntity mapToUserEntity(UserCreateDto user) {
        return new UserEntity(user.username(),
                user.password(),
                user.email(),
                user.technologies());
    }

    static UserResponseDto mapToUserResponseDto(UserEntity userEntity) {
        return new UserResponseDto(userEntity.getUsername(),
                userEntity.getNickname(),
                userEntity.getEmail(),
//                userEntity.getRoles(),
                userEntity.getTechnologies());
    }

    static GetLoggedUserDto mapToGetLoggedUser(UserEntity userEntity) {
        return new GetLoggedUserDto(
                userEntity.getUsername(),
                userEntity.getPassword());
    }


}
