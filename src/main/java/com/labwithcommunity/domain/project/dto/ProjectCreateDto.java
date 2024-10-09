package com.labwithcommunity.domain.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

public record ProjectCreateDto(
        String name,
        String description,
        String website,
        String wiki,
        String tracking,
        int methodologyId,
        int licenceId

) {
}
