package com.labwithcommunity.domain.user.dto;

import com.labwithcommunity.domain.user.Technologies;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserCreateDto(
        String nickname,
        String password,
        String email,
        Set<Technologies>technologies
) {
}
