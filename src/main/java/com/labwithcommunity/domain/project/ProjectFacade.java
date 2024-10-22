package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.project.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class ProjectFacade {

    private final ProjectFinder projectFinder;
    private final ProjectCreator projectCreator;

    public ProjectFetchDto createProject(ProjectCreateDto projectCreateDto, String username) {
        return projectCreator.createProject(projectCreateDto, username);
    }

    public Page<ProjectFetchDto> fetchAllProjects(Long creatorid, Long methodology, Long license, Pageable pageable) {
        return projectFinder.listAllProjects(creatorid,methodology,license,pageable);
    }
    public void deleteProject(Long projectId) {
        projectCreator.deleteProject(projectId);
    }
}
