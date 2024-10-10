package com.labwithcommunity.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserCreateDto(

        @Size(min = 4, message = "{username.size}")
        String username,

        @Size(min = 6, message = "{password.size}")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).+$",
                message = "{password.pattern}")
        String password,

        @Size(min = 6, message = "{confirm.password.size}")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).+$",
                message = "{confirm.password.pattern}")
        String confirmPassword,

        @Email(message = "{email.format}")
        String email
) {
}

