package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.tag.dto.AssignedTagCreateDto;
import com.labwithcommunity.domain.tag.dto.query.AssignedTagQueryDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
class AssignedTagService {

    private final AssignedTagRepository assignedTagRepository;


    AssignedTagCreateDto createAssignedTag(AssignedTagCreateDto assignedTagCreateDto) {
        AssignedTagEntity assignedTagEntity = new AssignedTagEntity(
                assignedTagCreateDto.tagId(),
                assignedTagCreateDto.assignerId(),
                assignedTagCreateDto.userId());
        assignedTagRepository.save(assignedTagEntity);
        return assignedTagCreateDto;
    }


}
