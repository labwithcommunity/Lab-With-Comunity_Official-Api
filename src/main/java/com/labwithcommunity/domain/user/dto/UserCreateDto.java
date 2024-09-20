package com.labwithcommunity.domain.user.dto;

import com.labwithcommunity.domain.user.Technologies;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserCreateDto(
        String nickname,
        String email,
        String password,
        Set<Technologies>technologies
) {
}
