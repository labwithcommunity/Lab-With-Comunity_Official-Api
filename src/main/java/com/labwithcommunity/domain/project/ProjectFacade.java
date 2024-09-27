package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProjectFacade {

    private final ProjectService projectService;


    public List<ProjectFetchDto> findProjectByOwner(String username) {
        return projectService.getProjectByOwner(username);
    }

    public ProjectFetchDto createProject(ProjectCreateDto projectCreateDto, String username) {
        return projectService.createProject(projectCreateDto, username);
    }

    public void signToProject(String projectName, String username) {
        projectService.signToProject(projectName, username);
    }

    public List<ProjectFetchDto> findByUserInProject(String username) {
        return projectService.findByUserInProject(username);
    }
}
