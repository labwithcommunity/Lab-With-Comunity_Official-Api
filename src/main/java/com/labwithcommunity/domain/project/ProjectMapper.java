package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.FindProjectsDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;

import java.util.List;
import java.util.stream.Collectors;

class ProjectMapper {

    static ProjectFetchDto mapToProjectFetchDto(ProjectEntity projectEntity) {
        return new ProjectFetchDto(
                projectEntity.getOwner(),
                projectEntity.getTitle(),
                projectEntity.getDescription(),
                projectEntity.getGithub(),
                projectEntity.getRating(),
                projectEntity.getParticipants()
        );
    }

    static List<FindProjectsDto> mapToProjectFindAllDto(List<ProjectEntity> projectEntity) {
        return projectEntity.stream()
                .map(project-> new FindProjectsDto(
                        project.getOwner(),
                        project.getTitle(),
                        project.getGithub(),
                        project.getRating(),
                        project.getDescription()
                )).toList();
    }

    static List<ProjectFetchDto> mapToProjectFetchDtoList(List<ProjectEntity> projectEntities) {
        return projectEntities.stream()
                .map(ProjectMapper::mapToProjectFetchDto)
                .collect(Collectors.toList());
    }
}
