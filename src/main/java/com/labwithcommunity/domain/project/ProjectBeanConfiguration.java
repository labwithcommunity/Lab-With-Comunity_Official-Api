package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProjectBeanConfiguration {

    @Bean
    ProjectFacade projectFacade(ProjectRepository projectRepository, UserFacade userFacade,
                                LicenceRepository licenceRepository,
                                MethodologyRepository methodologyRepository) {
        ProjectFinderService projectFinderService = new ProjectFinderService(projectRepository,userFacade);
        LicenceService licenceService = new LicenceService(licenceRepository);
        MethodologyService methodologyService = new MethodologyService(methodologyRepository);
        ProjectCreatorService projectCreatorService = new ProjectCreatorService(projectRepository,
                licenceService,methodologyService,userFacade);
        return new ProjectFacade(projectFinderService,projectCreatorService);
    }

}
