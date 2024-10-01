package com.labwithcommunity.domain.project;

class ProjectFacadeTest extends ProjectFacadeTestConfiguration {

    ProjectFacade projectFacade= new ProjectFacade(new ProjectFinderService(new InMemoryProjectRepository(),userFacade),
            new ProjectCreatorService(new InMemoryProjectRepository(),userFacade));


}
