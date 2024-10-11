package com.labwithcommunity.domain.project;

class ProjectFacadeTest extends ProjectFacadeTestConfiguration {

//    @Test
//    void shouldCreateFirstProjectSuccessfully() {
//        //Given
//        ProjectCreateDto projectRequest = new ProjectCreateDto("TestTitle",
//                "testDescriptionfirst", "gitt@git.com");
//
//        //When
//        ProjectFetchDto userTest = projectFacade.createProject(projectRequest, "userTest");
//
//        //Then
//        assertAll(
//                () -> assertNotNull(userTest),
//                () -> assertThat(userTest.owner()).isNotNull(),
//                () -> assertThat(userTest.title()).isEqualTo(projectRequest.title()),
//                () -> assertThat(projectRequest.description() ).isEqualTo(userTest.description()),
//                () -> assertThat(userTest.github()).isEqualTo(projectRequest.github()),
//                () -> assertEquals(1, inMemoryProjectRepository.findAll().size())
//        );
//    }
//
//    @Test
//    void shouldFindAllProjectSuccessfully() {
//        //Given
//        projectFacade.createProject(projectCreateDto, "userTest");
//        projectFacade.createProject(projectCreateDto2, "userTest");
//        projectFacade.createProject(projectCreateDto3, "userTest");
//
//        //When
//        List<ProjectFetchDto> projects = projectFacade.fetchAllProjects();
//
//        //Then
//        assertAll(
//                () -> assertNotNull(projects),
//                () -> assertThat(projects).hasSize(3)
//        );
//    }
}
