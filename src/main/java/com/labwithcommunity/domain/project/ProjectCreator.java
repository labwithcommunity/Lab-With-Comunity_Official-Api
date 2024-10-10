package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;

interface ProjectCreator {

    ProjectFetchDto createProject(ProjectCreateDto projectCreateDTO, String username);
}
