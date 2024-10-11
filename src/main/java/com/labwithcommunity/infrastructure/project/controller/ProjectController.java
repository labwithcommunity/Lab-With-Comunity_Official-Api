package com.labwithcommunity.infrastructure.project.controller;

import com.labwithcommunity.domain.project.ProjectFacade;
import com.labwithcommunity.domain.project.ProjectFinderService;
import com.labwithcommunity.domain.project.dto.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.ProjectFetchDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final ProjectFinderService projectFinderService;


    @PostMapping
    ResponseEntity<ProjectFetchDto> createProject(@RequestBody @Valid ProjectCreateDto projectCreateDto
            , @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        ProjectFetchDto project = projectFacade.createProject(projectCreateDto, username);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/my")
    ResponseEntity<List<ProjectFetchDto>> findProjectByOwner(@AuthenticationPrincipal UserDetails userDetails) {
        List<ProjectFetchDto> projectByOwner = projectFacade.findProjectByOwner(userDetails.getUsername());
        return ResponseEntity.ok(projectByOwner);
    }

    @PostMapping("sub/{id}")
    ResponseEntity<Boolean> signToProject(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        projectFacade.signToProject(id, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

//    @GetMapping
//    ResponseEntity<List<ProjectFetchDto>>findMyProjects(@AuthenticationPrincipal UserDetails userDetails ) {
//        String username = userDetails.getUsername();
//        List<ProjectFetchDto> byUserInProject = projectFacade.findByUserInProject(username);
//        return ResponseEntity.ok(byUserInProject);
//    }

    @GetMapping("/all")
    ResponseEntity<List<ProjectFetchDto>> fetchAll() {
        List<ProjectFetchDto> byUserInProject = projectFacade.fetchAllProjects();
        return ResponseEntity.ok(byUserInProject);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectFetchDto>> listAllProjects(
            @RequestParam(value = "user", required = false) String user,
            @RequestParam(value = "methodology", required = false) Long methodology,
            @RequestParam(value = "license", required = false) Long license,
            @RequestParam(value = "limit") Integer limit, // limit obowiązkowy
            @RequestParam(value = "page") Integer page) { // page obowiązkowy

        // Sprawdzenie, czy limit i page są większe od zera
        if (limit <= 0 || page < 0) {
            return ResponseEntity.badRequest().body(Page.empty());
        }

        Pageable pageable = PageRequest.of(page, limit);
        Page<ProjectFetchDto> projects = projectFinderService.listAllProjectsv2(user, methodology, license, pageable);

        return ResponseEntity.ok(projects);
    }
}
