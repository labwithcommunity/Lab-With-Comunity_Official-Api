package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;
import com.labwithcommunity.domain.project.exception.ProjectExceptionMessages;
import com.labwithcommunity.domain.project.exception.ProjectTitleAlreadyExistException;
import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
class ProjectCreatorService implements ProjectCreator {

    private final ProjectRepository projectRepository;
    private final LicenceService licenceService;
    private final MethodologyService methodologyService;
    private final UserFacade userFacade;


    @Override
    public ProjectFetchDto createProject(ProjectCreateDto projectCreateDTO, String username) {
        UserQueryDto owner = userFacade.getQueryUser(username);
        isExistByName(projectCreateDTO);
        ProjectEntity project = buildProjectEntity(projectCreateDTO, owner);
        projectRepository.save(project);
        log.info("Created project {}", project.getName());
        return ProjectMapper.mapToProjectFetchDto(project, owner.getNickname());
    }

    private ProjectEntity buildProjectEntity(ProjectCreateDto projectCreateDTO, UserQueryDto owner) {
        return new ProjectEntity(
                projectCreateDTO.name(),
                projectCreateDTO.description(),
                methodologyService.getMethodologyById(projectCreateDTO.methodologyId()),
                licenceService.getLicenceById(projectCreateDTO.licenceId()),
                projectCreateDTO.website(),
                projectCreateDTO.wiki(),
                projectCreateDTO.tracking(),
                owner.getId()
                );
    }

    private void isExistByName(ProjectCreateDto projectCreateDTO) {
        if (projectRepository.existsByName(projectCreateDTO.name())) {
            throw new ProjectTitleAlreadyExistException(
                    ProjectExceptionMessages.PROJECT_WITH_GIVEN_TITLE_ALREADY_EXISTS.getMessage());
        }
    }
}
