package com.labwithcommunity.infrastructure.user.security;

import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.GetLoggedUserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@AllArgsConstructor
class CustomUserDetailsService implements UserDetailsService {

    private final UserFacade userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GetLoggedUserDto userByUsername = userRepository.getLoggedUser(username);
        return getUser(userByUsername);
    }

    private User getUser(GetLoggedUserDto userDto) {
        return new User(
                userDto.username(),
                userDto.password(),
                Collections.emptyList());
    }
}