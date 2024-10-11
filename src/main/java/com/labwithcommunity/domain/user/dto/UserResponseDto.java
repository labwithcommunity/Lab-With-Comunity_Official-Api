package com.labwithcommunity.domain.user.dto;


import lombok.Builder;

@Builder
public record UserResponseDto(
        String username,
        String nickname,
        String email
) {
}
