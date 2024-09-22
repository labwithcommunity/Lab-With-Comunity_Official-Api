package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserFacade userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponseDto userByUsername = userRepository.findUserByUsername(username);
        return getUser(userByUsername);
    }

    private User getUser(UserResponseDto userDto){
        return new User(
                userDto.username(),
                userDto.password(),
                Collections.emptyList());
    }
}