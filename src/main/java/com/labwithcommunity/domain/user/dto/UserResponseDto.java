package com.labwithcommunity.domain.user.dto;


import lombok.Builder;

import java.util.Set;

@Builder
public record UserResponseDto(
        String username,
        String nickname,
        String email,
//        Set<UserMemberRoles> roles,
        Set<UserTechnologyDto> technologies

) {
}
