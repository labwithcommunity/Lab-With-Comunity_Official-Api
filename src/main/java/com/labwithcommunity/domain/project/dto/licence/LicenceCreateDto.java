package com.labwithcommunity.domain.project.dto.licence;

import jakarta.validation.constraints.Size;

public record LicenceCreateDto(
        @Size(min = 2, max = 16)
        String symbol,
        @Size(min = 5, max = 128)
        String name,
        @Size(min = 5)
        String description
) {
}
