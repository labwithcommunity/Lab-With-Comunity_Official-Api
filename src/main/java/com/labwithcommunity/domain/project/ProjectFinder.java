package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface ProjectFinder {

    Page<ProjectFetchDto> listAllProjects(Long creatorid, Long methodology, Long license, Pageable pageable);
}
