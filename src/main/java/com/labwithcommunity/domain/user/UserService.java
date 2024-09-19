package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.enums.UserRoles;
import com.labwithcommunity.domain.user.exception.UserExceptionMessages;
import com.labwithcommunity.domain.user.exception.UserAlreadyExistsException;
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
        isNicknameExist(userCreateDto);
        UserEntity savedUserEntity = userRepository.save(UserMapper.mapToUserEntity(userCreateDto));
        log.info("User registered: {}", savedUserEntity);
        return new UserCreateResponseDto(savedUserEntity.getNickname(),
                savedUserEntity.getNickname(),
                savedUserEntity.getEmail());
    }

    UserResponseDto getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .map(UserMapper::mapToUserResponseDto)
                .orElseThrow(()->new UserNotFoundException(UserExceptionMessages.USER_NOT_FOUND.getMessage()));
    }

    @Transactional
    public boolean addRoleToUser(Set<UserRoles> userRoles, String nickname) {
        Optional<UserEntity> byNickname = userRepository.findByNickname(nickname);
        UserEntity user = byNickname.orElseThrow(() -> new UserNotFoundException(UserExceptionMessages.USER_NOT_FOUND.getMessage()));
        log.info("Adding role to user: {}", user);
        return user.getRoles().addAll(userRoles);
    }

    private void isNicknameExist(UserCreateDto userCreateDto) {
        boolean userExists = userRepository.existsByNickname(userCreateDto.nickname());
        if (userExists) {
            throw new UserAlreadyExistsException(UserExceptionMessages.NICKNAME_ALREADY_EXIST.getMessage());
        }
    }
}
