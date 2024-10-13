package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.GetLoggedUserDto;
import com.labwithcommunity.domain.user.dto.UserResponseDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import com.labwithcommunity.domain.user.exception.UserExceptionMessages;
import com.labwithcommunity.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
class UserFinderService implements UserFinder{

    private final UserRepository userRepository;

    @Override
    public UserResponseDto findUserByUsername(String username) {
        UserEntity userEntityOrThrow = getUserEntity(username);
        return UserMapper.mapToUserResponseDto(userEntityOrThrow);
    }

    @Override
    public UserEntity getUserEntity(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessages.USER_NOT_FOUND.getMessage()));
    }

    @Override
    public GetLoggedUserDto getLoggedUser(String username) {
        return userRepository.findUsernameAndPasswordByNickname(username)
                .orElseThrow(() -> new UserNotFoundException(UserExceptionMessages.USER_NOT_FOUND.getMessage()));
    }

    public UserQueryDto getUserQuery(String username) {
        UserEntity user = getUserEntity(username);
        return UserMapper.mapToQueryDto(user);
    }
}
