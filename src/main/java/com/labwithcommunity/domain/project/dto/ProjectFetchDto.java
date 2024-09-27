package com.labwithcommunity.domain.project.dto;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;

import java.util.Set;

public record ProjectFetchDto(
        UserQueryDto owner,
        String title,
        Double rating,
        String description,
        Set<UserQueryDto> participants

) {

}
