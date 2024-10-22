package com.labwithcommunity.domain.technology;

import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import com.labwithcommunity.domain.technology.dto.UsedTechnologiesCreateDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TechnologyFacade {

    private final UsedTechnologiesService usedTechnologiesService;


    public void addUsedTechnologies(Long technologyId, ProjectQueryDto projectId, Integer level) {
         usedTechnologiesService.addTechnologyToProject(technologyId, projectId, level);
    }
}
