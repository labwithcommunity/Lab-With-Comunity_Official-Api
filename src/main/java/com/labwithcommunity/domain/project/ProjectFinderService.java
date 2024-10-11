package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.ProjectFetchDto;
import com.labwithcommunity.domain.project.exception.ProjectExceptionMessages;
import com.labwithcommunity.domain.project.exception.ProjectNotFoundException;
import com.labwithcommunity.domain.project.exception.UserSignedToProjectException;
import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class ProjectFinderService implements ProjectFinder {

    private final ProjectRepository projectRepository;
    private final UserFacade userFacade;

    @Override
    public List<ProjectFetchDto> getProjectByOwner(String owner) {
        UserQueryDto queryUser = userFacade.getQueryUser(owner);
        List<ProjectEntity> projectEntities = projectRepository.findAllByCreatorid(queryUser)
                .orElseThrow(() -> new ProjectNotFoundException(
                        ProjectExceptionMessages.NO_PROJECTS_FOUND_FOR_GIVEN_USER.name()));
        // ProjectMapper.mapToProjectFetchDtoList(projectEntities);
        return null;
    }

    @Override
    @Transactional
    public void signToProject(Long id, String username) {
        UserQueryDto user = userFacade.getQueryUser(username);
        validateUserProjectAssignment(user, id);
        Optional<ProjectEntity> projectOpt = projectRepository.findById(id);
        projectOpt.ifPresent(project -> project.getParticipants().add(user));
    }

    private void validateUserProjectAssignment(UserQueryDto user, Long id) {
        validateUserNotAssignedToProject(user, id);
        validateProjectExistsById(id);
    }

    private void validateUserNotAssignedToProject(UserQueryDto user, Long id) {
        if (projectRepository.existsByParticipantsContainingAndId(user, id)) {
            throw new UserSignedToProjectException(ProjectExceptionMessages.USER_ALREADY_SIGNED_TO_PROJECT.getMessage());
        }
    }

    private void validateProjectExistsById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException(ProjectExceptionMessages.PROJECT_ID_NOT_FOUND.getMessage());
        }
    }

    @Override
    public List<ProjectFetchDto> findByParticipant(String username) {
        UserQueryDto queryUser = userFacade.getQueryUser(username);
        List<ProjectEntity> projects =
                projectRepository.findProjectsByParticipant(queryUser)
                        .orElseThrow(() -> new ProjectNotFoundException(
                                ProjectExceptionMessages.NO_PROJECTS_FOUND_FOR_GIVEN_USER.name()));
        //   ProjectMapper.mapToProjectFetchDtoList(projects);
        return null;
    }

    @Override
    public Page<ProjectFetchDto> listAllProjects(Long creatorid, Long methodology, Long license, Pageable pageable) {
        Page<ProjectEntity> projectPage = projectRepository.findAllByFilters(creatorid, methodology, license, pageable);
        List<ProjectFetchDto> dto = ProjectMapper.mapToProjectFetchDtoList(projectPage.getContent());
        return new PageImpl<>(dto, pageable, projectPage.getTotalElements());
    }
}
