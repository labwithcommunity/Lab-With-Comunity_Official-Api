package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

class UserMapper {


//    static UserEntity mapToUserEntity(UserCreateDto user) {
//        return new UserEntity(user.username(),
//                user.password(),
//                user.email());
//    }

    static UserEntity mapToUserEntity(UserCreateDto userCreateDto, String username) {
        return UserEntity.builder().username(userCreateDto.email())
                .nickname(username)
                .password(userCreateDto.password())
                .isAdmin(false)
                .isApproved(false)
                .email(userCreateDto.email())
                .build();

    }
//    static UserResponseDto mapToUserResponseDto(UserEntity userEntity) {
//        return new UserResponseDto(userEntity.getUsername(),
//                userEntity.getNickname(),
//                userEntity.getEmail());
//                userEntity.getRoles(),
//                userEntity.getTechnologies().stream()
//                        .map(tech -> new UserTechnologyDto(
//                                tech.getProgrammingLanguage(),
//                                tech.getUserTechnologyForProgrammingLanguages()
//                        )).collect(Collectors.toSet()),
//                userEntity.getOwnedProjects());
//    }

//    static Technologies mapToTechnologies(UserTechnologyDto userTechnologyDto) {
//        return new Technologies(
//                userTechnologyDto.getProgrammingLanguage(),
//                userTechnologyDto.getUserTechnologyForProgrammingLanguages()
//        );
//    }

//    static Set<Technologies> mapToTechnologiesSet(Set<UserTechnologyDto> userTechnologyDto) {
//        return userTechnologyDto.stream()
//                .map(UserMapper::mapToTechnologies)
//                .collect(Collectors.toSet());
//    }

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
