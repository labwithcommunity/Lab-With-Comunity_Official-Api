package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.exception.PasswordMismatchException;
import com.labwithcommunity.domain.user.exception.UserAlreadyExistsException;
import com.labwithcommunity.domain.user.exception.UserExceptionMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@RequiredArgsConstructor
@Slf4j
class UserRegistrationService implements UserRegistration {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationsService confirmationsService;
    private final EmailService emailService;
    private final TokenEmailService tokenEmailService;

    @Override
    public UserCreateResponseDto register(UserCreateDto userCreateDto) {
        if (isUsernameExist(userCreateDto.email())) {
            throw new UserAlreadyExistsException(UserExceptionMessages.USERNAME_ALREADY_EXIST.getMessage());
        }
        if (!passwordsMatch(userCreateDto)) {
            throw new PasswordMismatchException(UserExceptionMessages.PASSWORDS_DO_NOT_MATCH.getMessage());
        }
        if (isEmailExist(userCreateDto.email())) {
            throw new UserAlreadyExistsException(UserExceptionMessages.EMAIL_ALREADY_EXIST.getMessage());
        }
        String username = userCreateDto.username();
        if (isNicknameExist(userCreateDto.username())) {
            username = addRandomNumberToNickname(username);
        }
        UserEntity userEntity = buildUserEntity(userCreateDto, username);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        confirmationsService.addConfirmation(userEntity);
        log.info("User registered: {}", savedUserEntity.getId());
        String token = tokenEmailService.createRegisterToken(userEntity.getEmail());
        emailService.sendRegisterEmail(savedUserEntity.getEmail(), token);
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

    @Override
    public String approveRegisterEmail(String token) {
                String email = tokenEmailService.getEmailByToken(token);
        if (email != null) {
            boolean approved = approveUserByEmail(email);
            tokenEmailService.invalidateRegisterToken(email);
            if (approved) {
                return "Your account has been successfully approved!";
            } else {
                return "Failed to approve the account. Please try again.";
            }
        } else {
            return "Invalid or expired token.";
        }
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
        int randomNumber = new Random().nextInt(10000);
        return nickname + randomNumber;
    }

    private boolean approveUserByEmail(String email) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(email);
        if (!optionalUserEntity.isPresent()) {
            log.warn("User not found by email: {}", email);
            return false;
        }
        UserEntity userEntity = optionalUserEntity.get();
        userEntity.setApproved(true);
        userRepository.save(userEntity);
        log.info("User {} has been approved by email.", userEntity.getUsername());
        return true;
    }
    @Override
    @Scheduled(fixedRate = 3600000)
    public void blockUnconfirmedUsers() {
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity userEntity : users) {
            LocalDateTime expirationDate = userEntity.getConfirmationEntity().getExpirationDate();
            if (!userEntity.isApproved() && LocalDateTime.now().isAfter(expirationDate)) {
                userEntity.setActive(false);
                userRepository.save(userEntity);
            }
        }
    }
}
