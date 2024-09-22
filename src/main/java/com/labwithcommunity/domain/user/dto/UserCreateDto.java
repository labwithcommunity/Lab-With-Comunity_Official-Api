package com.labwithcommunity.domain.user.dto;

import com.labwithcommunity.domain.user.Technologies;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
public record UserCreateDto(
        String username,
        String password,
        String email,
        Set<Technologies>technologies
) {
}
