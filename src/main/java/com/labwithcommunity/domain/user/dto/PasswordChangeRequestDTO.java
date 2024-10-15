package com.labwithcommunity.domain.user.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class PasswordChangeRequestDTO {

    @Email(message = "Invalid email format")
    private String email;
}
