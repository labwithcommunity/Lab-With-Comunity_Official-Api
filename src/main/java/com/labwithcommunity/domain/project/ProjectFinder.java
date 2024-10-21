package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

interface ProjectFinder {

    Page<ProjectFetchDto> listAllProjects(Long creatorid, Long methodology, Long license, Pageable pageable);
}
