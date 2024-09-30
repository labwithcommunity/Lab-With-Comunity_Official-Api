package com.labwithcommunity.domain.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public record ProjectCreateDto(

        @NotNull(message = "{validation.title.notNull}")
        @NotBlank(message = "{validation.title.notBlank}")
        String title,

        @NotNull(message = "{validation.description.notNull}")
        @NotBlank(message = "{validation.description.notBlank}")
        String description,

        @NotNull(message = "{validation.github.notNull}")
        @NotBlank(message = "{validation.github.notBlank}")
        @URL(message = "{validation.github.url}")
        String github

) {
}
