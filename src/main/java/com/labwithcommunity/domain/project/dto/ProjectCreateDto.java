package com.labwithcommunity.domain.project.dto;

public record ProjectCreateDto(String title,
                               String description,
                               String github,
                               String owner
) {
}
