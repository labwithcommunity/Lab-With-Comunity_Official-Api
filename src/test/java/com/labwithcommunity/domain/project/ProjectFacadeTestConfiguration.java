package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.UserFacadeTestConfiguration;

public class ProjectFacadeTestConfiguration extends UserFacadeTestConfiguration {

    ProjectFacade projectFacade= new ProjectFacade(new ProjectFinderService(new InMemoryProjectRepository(),userFacade),
            new ProjectCreatorService(new InMemoryProjectRepository(),userFacade));




}
