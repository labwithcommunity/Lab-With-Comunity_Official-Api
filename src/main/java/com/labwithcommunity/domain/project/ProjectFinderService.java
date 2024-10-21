package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;
import com.labwithcommunity.domain.project.exception.project.ProjectExceptionMessages;
import com.labwithcommunity.domain.project.exception.project.ProjectNotFoundException;
import com.labwithcommunity.domain.project.exception.project.UserSignedToProjectException;
import com.labwithcommunity.domain.tag.TagFacade;
import com.labwithcommunity.domain.tag.dto.query.AssignedTagQueryDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;
import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
