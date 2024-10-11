package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.ProjectFetchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface ProjectFinder {

    List<ProjectFetchDto> getProjectByOwner(String owner);
    void signToProject(Long id, String username);
    List<ProjectFetchDto> findByParticipant(String username);
    Page<ProjectFetchDto> listAllProjects(Long creatorid, Long methodology, Long license, Pageable pageable);
}
