package com.labwithcommunity.domain.user.dto;

import lombok.Builder;

@Builder
public record UserCreateDto(
        String nickname,
        String password,
        String email
) {
}
