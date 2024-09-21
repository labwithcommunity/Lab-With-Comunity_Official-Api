package com.labwithcommunity.domain.user.dto;

import com.labwithcommunity.domain.user.Technologies;
import com.labwithcommunity.domain.user.enums.UserMemberRoles;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserResponseDto(
        String username,
        String nickname,
        String email,
//        Set<UserMemberRoles> roles,
        Set<Technologies> technologies

) {
}
