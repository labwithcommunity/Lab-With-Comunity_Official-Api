package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
class ProjectFinderService implements ProjectFinder {

    private final ProjectRepository projectRepository;

    @Override
    public Page<ProjectFetchDto> listAllProjects(Long creatorid, Long methodology, Long license, Pageable pageable) {
        Page<ProjectEntity> projectPage = projectRepository.findAllByFilters(creatorid, methodology, license, pageable);


//        List<ProjectFetchDto> dto = projectPage.getContent().stream()
//                .map(project -> {
//                    AssignedTagQueryDto assignedTags = tagFacade.findAss(project.getProjectId());
//
//                    List<String> tagNames = assignedTags.getTags().stream()
//                            .map(TagQueryDto::getName)
//                            .toList();
//                   return ProjectMapper.mapToProjectFetchDto(project, tagNames);
//                })
//                .toList();

        List<ProjectFetchDto> dto = ProjectMapper.mapToProjectFetchDtoList(projectPage.getContent());
        return new PageImpl<>(dto, pageable, projectPage.getTotalElements());
    }
}
