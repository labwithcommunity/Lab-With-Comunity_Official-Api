package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserCreateDto;
import com.labwithcommunity.domain.user.dto.UserCreateResponseDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.exception.ExceptionMessages;
import com.labwithcommunity.domain.user.exception.UserAlreadyExistsException;
import com.labwithcommunity.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class UserService {

    private final UserRepository userRepository;

    UserCreateResponseDto register(UserCreateDto userCreateDto) {
        isNicknameExist(userCreateDto);
        UserEntity savedUserEntity = userRepository.save(UserMapper.mapToUserEntity(userCreateDto));
        return new UserCreateResponseDto(savedUserEntity.getNickname(),
                savedUserEntity.getNickname(),
                savedUserEntity.getEmail());
    }

    UserResponseDto getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .map(UserMapper::mapToUserResponseDto)
                .orElseThrow(()->new UserNotFoundException(ExceptionMessages.USER_NOT_FOUND.getMessage()));
    }

    private void isNicknameExist(UserCreateDto userCreateDto) {
        boolean userExists = userRepository.existsByNickname(userCreateDto.nickname());
        if (userExists) {
            throw new UserAlreadyExistsException(ExceptionMessages.NICKNAME_ALREADY_EXIST.getMessage());
        }
    }




}
