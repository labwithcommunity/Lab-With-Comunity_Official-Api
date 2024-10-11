package com.labwithcommunity.domain.project.dto.methodology;

import jakarta.validation.constraints.Size;

public record MethodologyCreateDto(
        @Size(min = 3, max = 48)
        String methodologyName
) {
}
