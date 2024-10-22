package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.project.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;

interface ProjectCreator {

    ProjectFetchDto createProject(ProjectCreateDto projectCreateDTO, String username);
    void deleteProject(Long projectId);
}
