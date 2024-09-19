package com.labwithcommunity.domain.user.dto;

import lombok.Builder;

@Builder
public record UserCreateResponseDto(
        String username,
        String nickname,
        String email
        ) {
}
