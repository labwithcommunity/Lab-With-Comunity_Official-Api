package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;
import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;

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
                projectEntity.getWebsite(),
                projectEntity.getWiki(),
                projectEntity.getTracking(),
                projectEntity.getMethodology().getMethodologyName(),
                projectEntity.getLicence().getName(),
                projectEntity.getTags().stream()
                        .map(assignedTag -> assignedTag.getTags().stream()
                                .map(TagQueryDto::getName)
                                .collect(Collectors.toList())
                        ).flatMap(List::stream)
                        .distinct()
                        .collect(Collectors.toList())
        );
    }


    static ProjectFetchDto mapToProjectAfterCreate(ProjectEntity projectEntity, List<String>tags) {
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
                tags
        );

    }

    static List<ProjectFetchDto> mapToProjectFetchDtoList(List<ProjectEntity> projectEntities ){
        return projectEntities.stream()
                .map(ProjectMapper::mapToProjectFetchDto)
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
