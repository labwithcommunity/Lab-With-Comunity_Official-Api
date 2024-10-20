package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.tag.TagFacade;
import com.labwithcommunity.domain.user.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProjectBeanConfiguration {

    @Bean
    ProjectFacade projectFacade(ProjectRepository projectRepository, UserFacade userFacade,
                                LicenceRepository licenceRepository,
                                MethodologyRepository methodologyRepository, TagFacade tagFacade) {
        ProjectFinderService projectFinderService = new ProjectFinderService(projectRepository,tagFacade,userFacade);
        LicenceService licenceService = new LicenceService(licenceRepository);
        MethodologyService methodologyService = new MethodologyService(methodologyRepository);

        ProjectCreatorService projectCreatorService = new ProjectCreatorService(projectRepository,
                licenceService,methodologyService,userFacade, tagFacade);
        return new ProjectFacade(projectFinderService,projectCreatorService);
    }

}
