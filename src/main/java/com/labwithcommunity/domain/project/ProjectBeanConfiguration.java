package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProjectBeanConfiguration {

    @Bean
    ProjectFacade projectFacade(ProjectRepository projectRepository, UserFacade userFacade) {
        ProjectFinderService projectFinderService = new ProjectFinderService(projectRepository,userFacade);
        ProjectCreatorService projectCreatorService = new ProjectCreatorService(projectRepository,userFacade);
        return new ProjectFacade(projectFinderService,projectCreatorService);
    }
}
