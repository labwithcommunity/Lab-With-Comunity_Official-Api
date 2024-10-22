package com.labwithcommunity.domain.technology;

import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class UsedTechnologiesService {

    private final UsedTechnologiesRepository usedTechnologiesRepository;
    private final TechnologyService technologyService;


     void addTechnologyToProject(Long technologyId, ProjectQueryDto projectId, Integer level ){
        TechnologyEntity technologyById = technologyService.findTechnologyById(technologyId);
        UsedTechnologiesEntity usedTechnologies = buildEntity(projectId,
                technologyById, level);
        usedTechnologiesRepository.save(usedTechnologies);
    }


    UsedTechnologiesEntity buildEntity(ProjectQueryDto projectQueryDto, TechnologyEntity technology, int level) {
        return new UsedTechnologiesEntity(
                projectQueryDto,
                technology,
                level
        );
    }
}
