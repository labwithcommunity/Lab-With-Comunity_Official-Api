package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProjectBeanConfiguration {

    @Bean
    ProjectService projectService(ProjectRepository projectRepository, UserFacade userFacade) {
        return new ProjectService(projectRepository,userFacade);
    }

    @Bean
    ProjectFacade projectFacade(ProjectService projectService) {
        return new ProjectFacade(projectService);
    }
}
