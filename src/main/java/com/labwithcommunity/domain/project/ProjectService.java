package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;
import com.labwithcommunity.domain.project.exception.ProjectNotFoundException;
import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserFacade userFacade;

    public ProjectFetchDto createProject(ProjectCreateDto projectCreateDTO, String username ) {
        UserQueryDto owner = userFacade.getQueryUser(username);
        if (owner == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }

        ProjectEntity project = new ProjectEntity();
        project.setTitle(projectCreateDTO.title());
        project.setDescription(projectCreateDTO.description());
        project.setOwner(owner);
        projectRepository.save(project);

        log.info("Created project {}", project.getTitle());
        return ProjectMapper.mapToProjectFetchDto(project);
    }

    public List<ProjectFetchDto> getProjectByOwner(String owner) {
        List<ProjectEntity> projectEntities = projectRepository.findAllByOwner(userFacade.getQueryUser(owner))
                .orElseThrow(()->new ProjectNotFoundException(""));
        return ProjectMapper.mapToProjectFetchDtoList(projectEntities);
    }

    @Transactional
    public void signToProject(String projectName, String username) {
        UserQueryDto user = userFacade.getQueryUser(username);

        ProjectEntity byTitle = projectRepository.findByTitle(projectName);
        byTitle.getParticipants().add(user);
    }

    public List<ProjectFetchDto> findByUserInProject(String username){
        UserQueryDto queryUser = userFacade.getQueryUser(username);
        List<ProjectEntity> projects =
                projectRepository.findProjectsByParticipantOrOwner(queryUser);
        return ProjectMapper.mapToProjectFetchDtoList(projects);
    }


}
