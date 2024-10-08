package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.FindProjectsDto;
import com.labwithcommunity.domain.project.dto.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProjectFacade {

    private final ProjectFinder projectFinder;
    private final ProjectCreator projectCreator;


    public List<ProjectFetchDto> findProjectByOwner(String username) {
        return projectFinder.getProjectByOwner(username);
    }

    public ProjectFetchDto createProject(ProjectCreateDto projectCreateDto, String username) {
        return projectCreator.createProject(projectCreateDto, username);
    }

    public void signToProject(Long id, String username) {
        projectFinder.signToProject(id, username);
    }

    public List<ProjectFetchDto> findByUserInProject(String username) {
        return projectFinder.findByParticipant(username);
    }

    public List<ProjectFetchDto> fetchAllProjects() {
        return projectFinder.listAllProjects();

    }
}
