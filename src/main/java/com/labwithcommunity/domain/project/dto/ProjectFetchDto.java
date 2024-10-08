package com.labwithcommunity.domain.project.dto;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;

import java.util.Set;

public record ProjectFetchDto(
        UserQueryDto owner,
        String title,
        String description,
        String github,
        Double rating,
        Set<UserQueryDto> participants

) {

}
