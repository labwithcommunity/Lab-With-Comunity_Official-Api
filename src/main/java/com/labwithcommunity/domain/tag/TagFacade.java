package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.tag.dto.AssignedTagCreateDto;
import com.labwithcommunity.domain.tag.dto.TagCreateDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TagFacade {

    private final TagService tagService;
    private final AssignedTagService assignedTagService;

    public TagQueryDto addNewTag(TagCreateDto tagCreateDto, UserQueryDto user) {
        return tagService.createOrAssignTag(tagCreateDto, user);
    }

    public void assignTag(AssignedTagCreateDto assignedTagCreateDto) {
        assignedTagService.createAssignedTag(assignedTagCreateDto);
    }



}
