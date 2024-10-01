package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ProjectFacadeTest extends ProjectFacadeTestConfiguration {

    @Test
    void shouldCreateFirstProjectSuccessfully() {
        //Given
        ProjectCreateDto projectCreateDto = new ProjectCreateDto("TestTitle","testDescription","git@git.com");

        //When
        ProjectFetchDto userTest = projectFacade.createProject(projectCreateDto, "userTest");

        assertAll(
                ()-> assertNotNull(userTest),
                ()-> assertThat(userTest.owner()).isNotNull(),
                ()-> assertThat(userTest.title()).isEqualTo(projectCreateDto.title()),
                ()-> assertThat(userTest.description()).isEqualTo(projectCreateDto.description()),
                ()-> assertThat(userTest.github()).isEqualTo(projectCreateDto.github()),
                ()-> assertEquals(1,inMemoryProjectRepository.findAll().size())
        );
    }
}
