package com.labwithcommunity.domain.project.dto;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;

public record FindProjectsDto(
        UserQueryDto owner,
        String title,
        String github,
        Double rating,
        String description
) {
}
