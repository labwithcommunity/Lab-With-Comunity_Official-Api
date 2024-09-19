package com.labwithcommunity.domain.user.dto;

import com.labwithcommunity.domain.user.UserRoles;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserResponseDto(
        String username,
        String nickname,
        String email,
        Set<UserRoles> roles
) {
}
