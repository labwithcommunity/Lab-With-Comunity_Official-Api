package com.labwithcommunity.domain.project.dto.project;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;

public record FindProjectsDto(
        UserQueryDto owner,
        String title,
        String github,
        String description
) {
}
