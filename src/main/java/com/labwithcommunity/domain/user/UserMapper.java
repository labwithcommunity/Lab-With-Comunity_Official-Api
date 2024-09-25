package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.dto.UserTechnologyDto;

import java.util.Set;
import java.util.stream.Collectors;

class UserMapper {

    static UserEntity mapToUserEntity(UserCreateDto user) {
        return new UserEntity(user.username(),
                user.password(),
                user.email());
    }

    static UserResponseDto mapToUserResponseDto(UserEntity userEntity) {
        return new UserResponseDto(userEntity.getUsername(),
                userEntity.getNickname(),
                userEntity.getEmail(),
//                userEntity.getRoles(),
                userEntity.getTechnologies().stream()
                        .map(tech -> new UserTechnologyDto(
                                tech.getProgrammingLanguage(),
                                tech.getUserTechnologyForProgrammingLanguages()
                        )).collect(Collectors.toSet()));
    }

    static Technologies mapToTechnologies(UserTechnologyDto userTechnologyDto) {
        return new Technologies(
                userTechnologyDto.getProgrammingLanguage(),
                userTechnologyDto.getUserTechnologyForProgrammingLanguages()
        );
    }

    static Set<Technologies> mapToTechnologiesSet(Set<UserTechnologyDto> userTechnologyDto) {
        return userTechnologyDto.stream()
                .map(UserMapper::mapToTechnologies)
                .collect(Collectors.toSet());
    }
}
