package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.exception.PasswordMismatchException;
import com.labwithcommunity.domain.user.exception.UserAlreadyExistsException;
import com.labwithcommunity.domain.user.exception.UserExceptionMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;


@RequiredArgsConstructor
@Slf4j
class UserRegistrationService implements UserRegistration {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserCreateResponseDto register(UserCreateDto userCreateDto) {
        if (isUsernameExist(userCreateDto.email())) {
            throw new UserAlreadyExistsException(UserExceptionMessages.USERNAME_ALREADY_EXIST.getMessage());
        }
        if (!passwordsMatch(userCreateDto)) {
            throw new PasswordMismatchException(UserExceptionMessages.PASSWORDS_DO_NOT_MATCH.getMessage());
        }
        if(isEmailExist(userCreateDto.email())){
            throw new UserAlreadyExistsException(UserExceptionMessages.EMAIL_ALREADY_EXIST.getMessage());
        }
        String username = userCreateDto.username();
        if(isNicknameExist(userCreateDto.username())){
            username = addRandomNumberToNickname(username);
        }
        UserEntity userEntity = buildUserEntity(userCreateDto, username);
        System.out.println(userEntity);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        System.out.println(savedUserEntity);
        log.info("User registered: {}", savedUserEntity.getId());
        return new UserCreateResponseDto(
                savedUserEntity.getUsername(),
                savedUserEntity.getNickname(),
                savedUserEntity.getEmail()
        );
    }

    private UserEntity buildUserEntity(UserCreateDto userCreateDto, String username) {
        UserEntity userEntity = UserMapper.mapToUserEntity(userCreateDto, username);
        userEntity.setPassword(passwordEncoder.encode(userCreateDto.password()));
        return userEntity;
    }

    @Override
    public boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }

    private boolean passwordsMatch(UserCreateDto userCreateDto) {
        return userCreateDto.password().equals(userCreateDto.confirmPassword());
    }

    private boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean isNicknameExist(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    private String addRandomNumberToNickname(String nickname) {
        int randomNumber = new Random().nextInt(1000);
        return nickname + randomNumber;
    }
}
