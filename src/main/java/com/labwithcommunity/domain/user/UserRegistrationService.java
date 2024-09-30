package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.exception.UserAlreadyExistsException;
import com.labwithcommunity.domain.user.exception.UserExceptionMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@Slf4j
class UserRegistrationService implements UserRegistration {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserCreateResponseDto register(UserCreateDto userCreateDto) {
        if (isUsernameExist(userCreateDto.username())) {
            throw new UserAlreadyExistsException(UserExceptionMessages.USERNAME_ALREADY_EXIST.getMessage());
        }
        UserEntity userEntity = buildUserEntity(userCreateDto);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        log.info("User registered: {}", savedUserEntity.getId());
        return new UserCreateResponseDto(
                savedUserEntity.getUsername(),
                savedUserEntity.getNickname(),
                savedUserEntity.getEmail()
        );
    }

    private UserEntity buildUserEntity(UserCreateDto userCreateDto) {
        UserEntity userEntity = UserMapper.mapToUserEntity(userCreateDto);
        userEntity.setPassword(passwordEncoder.encode(userCreateDto.password()));
        userEntity.setRole("USER");
        userEntity.setTechnologies(UserMapper.mapToTechnologiesSet(userCreateDto.technologies()));
        return userEntity;
    }

    @Override
    public boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }
}
