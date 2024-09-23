package com.labwithcommunity.domain.user.dto;

import com.labwithcommunity.domain.user.Technologies;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserCreateDto(

        @Size(min = 4, message = "{username.size}")
        String username,

        @Size(min = 4, message = "{password.size}")
        String password,

        @Email(message = "{email.format}")
        String email,

        @NotNull(message = "{technologies.not.null}")
        Set<Technologies> technologies
) {
}

