package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.project.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;
import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import com.labwithcommunity.domain.project.exception.project.ProjectExceptionMessages;
import com.labwithcommunity.domain.project.exception.project.ProjectTitleAlreadyExistException;
import com.labwithcommunity.domain.tag.TagFacade;
import com.labwithcommunity.domain.tag.dto.AssignedTagCreateDto;
import com.labwithcommunity.domain.technology.TechnologyFacade;
import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
class ProjectCreatorService implements ProjectCreator {

    private final ProjectRepository projectRepository;
    private final LicenceService licenceService;
    private final MethodologyService methodologyService;
    private final UserFacade userFacade;
    private final TagFacade tagFacade;
    private final TechnologyFacade technologyFacade;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ProjectFetchDto createProject(ProjectCreateDto projectCreateDTO, String username) {
        isExistByName(projectCreateDTO);
        UserQueryDto creator = userFacade.getQueryUser(username);
        ProjectEntity project = buildProjectEntity(projectCreateDTO, creator);
        ProjectEntity savedProject = projectRepository.save(project);
        ProjectQueryDto projectQueryDto = ProjectMapper.mapToQueryDto(project);
        technologyFacade.addUsedTechnologies(projectCreateDTO.technologyId(), projectQueryDto,projectCreateDTO.level());
        List<String> tags = tagFacade.addTags(projectCreateDTO, creator);
        AssignedTagCreateDto assignedTagCreateDto = new AssignedTagCreateDto(tags, projectQueryDto, creator);
        tagFacade.assignTag(assignedTagCreateDto);
        log.info("Created project {}", project.getName());
        return ProjectMapper.mapToProjectAfterCreate(savedProject,tags);
    }

    private ProjectEntity buildProjectEntity(ProjectCreateDto projectCreateDTO, UserQueryDto creator) {
        return new ProjectEntity(
                projectCreateDTO.name(),
                projectCreateDTO.description(),
                methodologyService.getMethodologyById(projectCreateDTO.methodologyId()),
                licenceService.getLicenceById(projectCreateDTO.licenceId()),
                projectCreateDTO.website(),
                projectCreateDTO.wiki(),
                projectCreateDTO.tracking(),
                creator
        );
    }

    private void isExistByName(ProjectCreateDto projectCreateDTO) {
        if (projectRepository.existsByName(projectCreateDTO.name())) {
            throw new ProjectTitleAlreadyExistException(
                    ProjectExceptionMessages.PROJECT_WITH_GIVEN_TITLE_ALREADY_EXISTS.getMessage());
        }
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
