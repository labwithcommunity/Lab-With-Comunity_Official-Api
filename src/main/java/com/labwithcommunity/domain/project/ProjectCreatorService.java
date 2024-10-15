package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.project.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;
import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import com.labwithcommunity.domain.project.exception.project.ProjectExceptionMessages;
import com.labwithcommunity.domain.project.exception.project.ProjectTitleAlreadyExistException;
import com.labwithcommunity.domain.tag.TagFacade;
import com.labwithcommunity.domain.tag.dto.AssignedTagCreateDto;
import com.labwithcommunity.domain.tag.dto.TagCreateDto;
import com.labwithcommunity.domain.tag.dto.query.AssignedTagQueryDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;
import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
class ProjectCreatorService implements ProjectCreator {

    private final ProjectRepository projectRepository;
    private final LicenceService licenceService;
    private final MethodologyService methodologyService;
    private final UserFacade userFacade;
    private final TagFacade tagFacade;


    @Override
    public ProjectFetchDto createProject(ProjectCreateDto projectCreateDTO, String username) {
        UserQueryDto creator = userFacade.getQueryUser(username);
        isExistByName(projectCreateDTO);
        ProjectEntity project = buildProjectEntity(projectCreateDTO, creator);
        ProjectEntity save = projectRepository.save(project);
        ProjectQueryDto projectQueryDto = ProjectMapper.mapToQueryDto(save);
        List<String> strings = addTagsInChain(projectCreateDTO, creator, projectQueryDto);
        log.info("Created project {}", project.getName());
        return ProjectMapper.mapToProjectFetchDto(project,strings);
    }

    List<String> addTagsInChain(ProjectCreateDto projectCreateDTO,
                                UserQueryDto creator,
                                ProjectQueryDto projectQueryDto) {
        List<String> tagNames = new ArrayList<>();

        for (TagCreateDto tag : projectCreateDTO.tags()) {
            TagQueryDto tagQueryDto = tagFacade.addNewTag(tag, creator);
            AssignedTagCreateDto assignedTagCreateDto = new AssignedTagCreateDto(
                    tagQueryDto, projectQueryDto, creator
            );
            tagFacade.assignTag(assignedTagCreateDto);
            tagNames.add(tagQueryDto.getName());
        }

        return tagNames;
    }





    private ProjectEntity buildProjectEntity(ProjectCreateDto projectCreateDTO, UserQueryDto creator) {
        return new ProjectEntity(
                projectCreateDTO.name(),
                projectCreateDTO.description(),
                methodologyService.getMethodologyById(projectCreateDTO.methodologyId()),
                licenceService.getLicenceById(projectCreateDTO.licenceId()),
                projectCreateDTO.website(),
                projectCreateDTO.wiki(),
                projectCreateDTO.tracking(),
                creator
        );
    }

    private void isExistByName(ProjectCreateDto projectCreateDTO) {
        if (projectRepository.existsByName(projectCreateDTO.name())) {
            throw new ProjectTitleAlreadyExistException(
                    ProjectExceptionMessages.PROJECT_WITH_GIVEN_TITLE_ALREADY_EXISTS.getMessage());
        }
    }
}
