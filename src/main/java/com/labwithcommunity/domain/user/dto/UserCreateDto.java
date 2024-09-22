package com.labwithcommunity.domain.user.dto;

import com.labwithcommunity.domain.user.Technologies;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import jakarta.validation.constraints.Email;

import java.util.Set;

@Builder
public record UserCreateDto(

        @Size(min = 4, message = "User name must be at least 4 characters long")
        String username,

        @Size(min = 4, message = "Password must be at least 4 characters long")
        String password,

        @Email(message = "Invalid email format")
        String email,

        @NotNull(message = "Technologies must not be null")
        Set<Technologies> technologies
) {
}

