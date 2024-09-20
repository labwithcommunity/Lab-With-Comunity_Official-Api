package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.enums.UserMemberRoles;
import com.labwithcommunity.domain.user.exception.UserAlreadyExistsException;
import com.labwithcommunity.domain.user.exception.UserExceptionMessages;
import com.labwithcommunity.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
class UserService {

    private final UserRepository userRepository;

    UserCreateResponseDto register(UserCreateDto userCreateDto) {
        if (isNicknameExist(userCreateDto.nickname())) {
            throw new UserAlreadyExistsException(UserExceptionMessages.NICKNAME_ALREADY_EXIST.getMessage());
        }

        try {
            UserEntity savedUserEntity = userRepository.save(UserMapper.mapToUserEntity(userCreateDto));
            log.info("User registered: {}", savedUserEntity);
            return new UserCreateResponseDto(
                    savedUserEntity.getNickname(),
                    savedUserEntity.getNickname(),
                    savedUserEntity.getEmail()
            );
        } catch (Exception exception) {
            log.error("Error registering user: {}", exception.getMessage());
            throw exception;
        }
    }

    private boolean isNicknameExist(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    UserResponseDto getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .map(UserMapper::mapToUserResponseDto)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessages.USER_NOT_FOUND.getMessage()));
    }

    @Transactional
    boolean addRoleToUser(Set<UserMemberRoles> userMemberRoles, String nickname) {
        Optional<UserEntity> byNickname = userRepository.findByNickname(nickname);
        UserEntity user = byNickname.orElseThrow(() -> new UserNotFoundException(UserExceptionMessages.USER_NOT_FOUND.getMessage()));
        log.info("Adding role to user: {}", user);
        return user.getRoles().addAll(userMemberRoles);
    }
}
