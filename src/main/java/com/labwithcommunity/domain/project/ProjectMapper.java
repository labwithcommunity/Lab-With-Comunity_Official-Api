package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;

import java.util.List;
import java.util.stream.Collectors;

class ProjectMapper {

    static ProjectFetchDto mapToProjectFetchDto(ProjectEntity projectEntity) {
        return new ProjectFetchDto(
                projectEntity.getProjectId(),
                projectEntity.getName(),
                projectEntity.getDescription(),
                projectEntity.getCreated(),
                projectEntity.getCreatorid().getNickname(),
                projectEntity.getMethodology().getMethodologyName(),
                projectEntity.getLicence().getName(),
                projectEntity.getWebsite(),
                projectEntity.getWiki(),
                projectEntity.getWiki()

        );
    }

//    static List<FindProjectsDto> mapToProjectFindAllDto(List<ProjectEntity> projectEntity) {
//        return projectEntity.stream()
//                .map(project-> new FindProjectsDto(
//                        project.getCreator(),
//                        project.getName(),
//                        project.getWebsite(),
//                        project.getDescription()
//                )).toList();
//    }

    static List<ProjectFetchDto> mapToProjectFetchDtoList(List<ProjectEntity> projectEntities) {
        return projectEntities.stream()
                .map(ProjectMapper::mapToProjectFetchDto)
                .collect(Collectors.toList());
    }

}
