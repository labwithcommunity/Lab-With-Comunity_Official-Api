package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;

import java.util.List;
import java.util.stream.Collectors;

class ProjectMapper {

    ProjectEntity mapToProjectEntity(ProjectCreateDto projectCreateDto) {
        return new ProjectEntity(
                projectCreateDto.title(),
                projectCreateDto.description()
//                projectCreateDto.owner()
        );
    }


    static ProjectFetchDto mapToProjectFetchDto(ProjectEntity projectEntity) {
        return new ProjectFetchDto(
                projectEntity.getOwner(),
                projectEntity.getTitle(),
                projectEntity.getRating(),
                projectEntity.getDescription(),
                projectEntity.getParticipants()
        );
    }

    static List<ProjectFetchDto> mapToProjectFetchDtoList(List<ProjectEntity> projectEntities) {
        return projectEntities.stream()
                .map(ProjectMapper::mapToProjectFetchDto)
                .collect(Collectors.toList());
    }
}
