package com.labwithcommunity.domain.user.dto;

public record UserCreateResponseDto(
        String username,
        String nickname,
        String email
        ) {
}
