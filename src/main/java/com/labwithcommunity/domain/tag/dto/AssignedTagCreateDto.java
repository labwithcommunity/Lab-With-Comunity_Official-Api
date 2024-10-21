package com.labwithcommunity.domain.tag.dto;

import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;

import java.util.List;

public record AssignedTagCreateDto (
        List<String> tag,
        ProjectQueryDto project,
        UserQueryDto user
){
}
