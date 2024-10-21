package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.project.dto.project.ProjectCreateDto;
import com.labwithcommunity.domain.tag.dto.AssignedTagCreateDto;
import com.labwithcommunity.domain.tag.dto.query.AssignedTagQueryDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TagFacade {

    private final TagService tagService;
    private final AssignedTagService assignedTagService;

    public List<String> addTags(ProjectCreateDto projectCreateDTO, UserQueryDto creator) {
       return tagService.addTagsInChain(projectCreateDTO, creator);
    }

    public AssignedTagCreateDto assignTag(AssignedTagCreateDto assignedTagCreateDto){
        return assignedTagService.createAssignedToProject(assignedTagCreateDto);
     }
}
