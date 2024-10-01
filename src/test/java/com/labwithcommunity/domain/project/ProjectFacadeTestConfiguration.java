package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.ProjectCreateDto;
import com.labwithcommunity.domain.user.UserFacadeTestConfiguration;
import org.junit.jupiter.api.BeforeEach;

public class ProjectFacadeTestConfiguration extends UserFacadeTestConfiguration {


    InMemoryProjectRepository inMemoryProjectRepository = new InMemoryProjectRepository();

    ProjectFacade projectFacade= new ProjectFacade(new ProjectFinderService(inMemoryProjectRepository,userFacade),
            new ProjectCreatorService(inMemoryProjectRepository,userFacade));


    ProjectCreateDto projectCreateDto;


    @BeforeEach
    public void setup() {
        registerTestUser();
        registerTestUserWithTwoTechnologies();
        projectCreateDto =new ProjectCreateDto("TestTitle","testDescription","git@git.com");
    }
}
