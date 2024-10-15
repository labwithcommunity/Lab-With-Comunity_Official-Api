package com.labwithcommunity.domain.tag.dto;

import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;

public record AssignedTagCreateDto (
        TagQueryDto tagId,
        ProjectQueryDto assignerId,
        UserQueryDto userId
){
}
