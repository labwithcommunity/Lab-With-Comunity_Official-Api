package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.FindProjectsDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;

import java.util.List;

interface ProjectFinder {

    List<ProjectFetchDto> getProjectByOwner(String owner);
    void signToProject(Long id, String username);
    List<ProjectFetchDto> findByParticipant(String username);
    List<FindProjectsDto> listAllProjects();
}
