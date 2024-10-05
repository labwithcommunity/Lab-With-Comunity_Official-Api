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
    private final UserFacade userFacade;


    @Override
    public ProjectFetchDto createProject(ProjectCreateDto projectCreateDTO, String username) {
        UserQueryDto owner = userFacade.getQueryUser(username);
        isExistByTitle(projectCreateDTO);
        ProjectEntity project = buildProjectEntity(projectCreateDTO, owner);
        projectRepository.save(project);
        log.info("Created project {}", project.getTitle());
        return ProjectMapper.mapToProjectFetchDto(project);
    }

    private static ProjectEntity buildProjectEntity(ProjectCreateDto projectCreateDTO, UserQueryDto owner) {
        return new ProjectEntity(
                projectCreateDTO.title(),
                projectCreateDTO.github(),
                projectCreateDTO.description(),
                owner);
    }

    private void isExistByTitle(ProjectCreateDto projectCreateDTO) {
        if (projectRepository.existsByTitle(projectCreateDTO.title())) {
            throw new ProjectTitleAlreadyExistException(
                    ProjectExceptionMessages.PROJECT_WITH_GIVEN_TITLE_ALREADY_EXISTS.getMessage());
        }
    }
}
