package com.labwithcommunity.infrastructure.project.controller;

import com.labwithcommunity.domain.project.ProjectFacade;
import com.labwithcommunity.domain.project.dto.FindProjectsDto;
import com.labwithcommunity.domain.project.dto.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
@RequiredArgsConstructor
class ProjectController {

    private final ProjectFacade projectFacade;

    @PostMapping
    ResponseEntity<ProjectFetchDto>createProject(@RequestBody @Valid ProjectCreateDto projectCreateDto
            ,@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        ProjectFetchDto project = projectFacade.createProject(projectCreateDto,username);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/my")
    ResponseEntity<List<ProjectFetchDto>>findProjectByOwner(@AuthenticationPrincipal UserDetails userDetails) {
        List<ProjectFetchDto> projectByOwner = projectFacade.findProjectByOwner(userDetails.getUsername());
        return ResponseEntity.ok(projectByOwner);
    }

    @PostMapping("sub")
    ResponseEntity<Boolean>signToProject(String title, @AuthenticationPrincipal UserDetails userDetails) {
        projectFacade.signToProject(title,userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<ProjectFetchDto>>findMyProjects(@AuthenticationPrincipal UserDetails userDetails ) {
        String username = userDetails.getUsername();
        List<ProjectFetchDto> byUserInProject = projectFacade.findByUserInProject(username);
        return ResponseEntity.ok(byUserInProject);
    }

    @GetMapping("/all")
    ResponseEntity<List<FindProjectsDto>>fetchAll() {
        List<FindProjectsDto> byUserInProject = projectFacade.fetchAllProjects();
        return ResponseEntity.ok(byUserInProject);
    }
}
