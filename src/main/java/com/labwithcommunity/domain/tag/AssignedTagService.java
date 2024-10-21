package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.tag.dto.AssignedTagCreateDto;
import com.labwithcommunity.domain.tag.dto.query.AssignedTagQueryDto;
import com.labwithcommunity.domain.tag.exception.AssignedAlreadyToProject;
import com.labwithcommunity.domain.tag.exception.AssignedExceptionMessages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
class AssignedTagService {

    private final AssignedTagRepository assignedTagRepository;
    private final TagService tagService;


    public AssignedTagCreateDto createAssignedToProject(AssignedTagCreateDto assignedTagCreateDto) {
        List<TagEntity> tagEntities = tagService.findTagsByNameIn(assignedTagCreateDto.tag());
        AssignedTagEntity assignedTagEntity = new AssignedTagEntity(
                tagEntities,
                assignedTagCreateDto.project(),
                assignedTagCreateDto.user()
        );
        if (assignedTagRepository.existsByProjectProjectId(assignedTagEntity.getProject().getProjectId())){
            throw new AssignedAlreadyToProject(AssignedExceptionMessages.ALREADY_ASSIGNED_TO_PROJECT.getMessage());
        }
        assignedTagRepository.save(assignedTagEntity);
        log.info("Assigned tag created");
        return assignedTagCreateDto;
    }

    public AssignedTagQueryDto findAll(Long id){
        AssignedTagEntity assignedTagEntity = assignedTagRepository.findByProjectProjectId(id);
        return AssignedTagMapper.mapToAssignedQuery(assignedTagEntity);
    }
}
