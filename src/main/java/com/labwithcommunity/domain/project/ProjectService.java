package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.FindProjectsDto;
import com.labwithcommunity.domain.project.dto.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;
import com.labwithcommunity.domain.project.exception.*;
import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserFacade userFacade;

    public ProjectFetchDto createProject(ProjectCreateDto projectCreateDTO, String username) {
        UserQueryDto owner = fetchUserInfo(username);
        isExistByTitle(projectCreateDTO);
        ProjectEntity project = buildProjectEntity(projectCreateDTO, owner);
        projectRepository.save(project);
        log.info("Created project {}", project.getTitle());
        return ProjectMapper.mapToProjectFetchDto(project);
    }

    private static ProjectEntity buildProjectEntity(ProjectCreateDto projectCreateDTO, UserQueryDto owner) {
        return new ProjectEntity(
                projectCreateDTO.title(),
                projectCreateDTO.description(),
                projectCreateDTO.github(),
                owner);
    }

    private void isExistByTitle(ProjectCreateDto projectCreateDTO) {
        if (projectRepository.existsByTitle(projectCreateDTO.title())) {
            throw new ProjectTitleAlreadyExistException(
                    ProjectExceptionMessages.PROJECT_WITH_GIVEN_TITLE_ALREADY_EXISTS.getMessage());
        }
    }

    public List<ProjectFetchDto> getProjectByOwner(String owner) {
        List<ProjectEntity> projectEntities = projectRepository.findAllByOwner(userFacade.getQueryUser(owner))
                .orElseThrow(() -> new ProjectNotFoundException(
                        ProjectExceptionMessages.NO_PROJECTS_FOUND_FOR_GIVEN_USER.name()));
        return ProjectMapper.mapToProjectFetchDtoList(projectEntities);
    }

    @Transactional
    public void signToProject(Long id, String username) {
        UserQueryDto user = fetchUserInfo(username);
        validateUserProjectAssignment(user, id);
        Optional<ProjectEntity> projectOpt = projectRepository.findById(id);
        projectOpt.ifPresent(project -> project.getParticipants().add(user));
    }

    private void validateUserProjectAssignment(UserQueryDto user, Long id) {
        if (projectRepository.existsByParticipantsContainingAndId(user, id)) {
            throw new UserSignedToProjectException(
                    ProjectExceptionMessages.USER_ALREADY_SIGNED_TO_PROJECT.getMessage());
        }
        if(!projectRepository.existsById(id)){
            throw new ProjectNotFoundException(
                    ProjectExceptionMessages.PROJECT_ID_NOT_FOUND.getMessage());
        }
    }

    public List<ProjectFetchDto> findByParticipant(String username) {
        UserQueryDto queryUser = fetchUserInfo(username);
        List<ProjectEntity> projects =
                projectRepository.findProjectsByParticipant(queryUser)
                        .orElseThrow(() -> new ProjectNotFoundException(
                                ProjectExceptionMessages.NO_PROJECTS_FOUND_FOR_GIVEN_USER.name()));
        return ProjectMapper.mapToProjectFetchDtoList(projects);
    }

    private UserQueryDto fetchUserInfo(String username) {
        return userFacade.getQueryUser(username);
    }

    List<FindProjectsDto> listAllProjects() {
        List<ProjectEntity> allProjects = projectRepository.findAll();
        return ProjectMapper.mapToProjectFindAllDto(allProjects);
    }
}
