package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.GetLoggedUserDto;
import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.exception.UserAlreadyExistsException;
import com.labwithcommunity.domain.user.exception.UserExceptionMessages;
import com.labwithcommunity.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Slf4j
class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    UserCreateResponseDto register(UserCreateDto userCreateDto) {
        if (isUsernameExist(userCreateDto.username())) {
            throw new UserAlreadyExistsException(UserExceptionMessages.USERNAME_ALREADY_EXIST.getMessage());
        }
        try {
            UserEntity userEntity = UserMapper.mapToUserEntity(userCreateDto);
            userEntity.setPassword(passwordEncoder.encode(userCreateDto.password()));
            userEntity.setRole("USER");
            userEntity.setTechnologies(UserMapper.mapToTechnologiesSet(userCreateDto.technologies()));
            UserEntity savedUserEntity = userRepository.save(userEntity);
            log.info("User registered: {}", savedUserEntity);
            return new UserCreateResponseDto(
                    savedUserEntity.getUsername(),
                    savedUserEntity.getUsername(),
                    savedUserEntity.getEmail()
            );
        } catch (Exception exception) {
            log.error("Error registering user: {}", exception.getMessage());
            throw exception;
        }
    }

    private boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserResponseDto getUserByUsername(String username) {
        UserEntity userEntityOrThrow = getUserEntityOrThrow(username);
        return UserMapper.mapToUserResponseDto(userEntityOrThrow);
    }

     UserEntity getUserEntityOrThrow(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessages.USER_NOT_FOUND.getMessage()));
    }

    public GetLoggedUserDto getLoggedUser(String username) {
        return userRepository.findUsernameAndPasswordByNickname(username)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessages.USER_NOT_FOUND.getMessage()));
    }

//    @Transactional
//    boolean addRoleToUser(Set<UserMemberRoles> userMemberRoles, String username) {
//        Optional<UserEntity> byUsername = userRepository.findByUsername(username);
//        UserEntity user = byUsername.orElseThrow(() -> new UserNotFoundException(UserExceptionMessages.USER_NOT_FOUND.getMessage()));
//        log.info("Adding role to user: {}", user);
//        return user.getRoles().addAll(userMemberRoles);
//    }
}
