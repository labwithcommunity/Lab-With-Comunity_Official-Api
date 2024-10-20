package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;
import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;

import java.util.List;
import java.util.stream.Collectors;

class ProjectMapper {

    static ProjectFetchDto mapToProjectFetchDto(ProjectEntity projectEntity,List<String>tags) {

        return new ProjectFetchDto(
                projectEntity.getProjectId(),
                projectEntity.getName(),
                projectEntity.getDescription(),
                projectEntity.getCreated(),
                projectEntity.getCreatorid().getNickname(),
                projectEntity.getWebsite(),
                projectEntity.getWiki(),
                projectEntity.getTracking(),
                projectEntity.getMethodology().getMethodologyName(),
                projectEntity.getLicence().getName(),
                tags // Przypisujemy listę nazw tagów
        );
    }

    static List<ProjectFetchDto> mapToProjectFetchDtoList(List<ProjectEntity> projectEntities,List<String>tags) {
        return projectEntities.stream()
                .map(tag -> mapToProjectFetchDto(tag,tags))
                .collect(Collectors.toList());
    }

    static ProjectQueryDto mapToQueryDto(ProjectEntity projectEntity) {
        return new ProjectQueryDto(
                projectEntity.getProjectId(),
                projectEntity.getName(),
                projectEntity.getDescription(),
                projectEntity.getCreatorid()
        );
    }

}
