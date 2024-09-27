package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.ProjectFetchDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProjectFacade {

    private final ProjectService projectService;


    public List<ProjectFetchDto> projectFetch(String username){
        return projectService.getProjectByOwner(username);
    }
}
