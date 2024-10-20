package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.tag.dto.AssignedTagCreateDto;
import com.labwithcommunity.domain.tag.dto.query.AssignedTagQueryDto;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
class AssignedTagService {

    private final AssignedTagRepository assignedTagRepository;
    private final TagService tagService;


    public AssignedTagCreateDto createAssignedToProject(AssignedTagCreateDto assignedTagCreateDto) {
        List<TagEntity> tagEntities = new ArrayList<>();

        for (String tagName : assignedTagCreateDto.tag()) {
            TagEntity tagByName = tagService.findTagByName(tagName);
            tagEntities.add(tagByName);
        }
        AssignedTagEntity assignedTagEntity = new AssignedTagEntity(
                tagEntities,
                assignedTagCreateDto.project(),
                assignedTagCreateDto.user()
        );
        assignedTagRepository.save(assignedTagEntity);
        return assignedTagCreateDto;
    }

    public AssignedTagQueryDto findAll(Long id){
        AssignedTagEntity assignedTagEntity = assignedTagRepository.findByProjectProjectId(id);
        return AssignedTagMapper.mapToAssignedQuery(assignedTagEntity);
    }
}
