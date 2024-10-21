package com.labwithcommunity.infrastructure.project.controller;

import com.labwithcommunity.domain.project.ProjectFacade;
import com.labwithcommunity.domain.project.dto.project.ProjectCreateDto;
import com.labwithcommunity.domain.project.dto.project.ProjectFetchDto;
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

    @PostMapping
    ResponseEntity<ProjectFetchDto> createProject(@RequestBody @Valid ProjectCreateDto projectCreateDto
            , @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        ProjectFetchDto project = projectFacade.createProject(projectCreateDto, username);
        return ResponseEntity.ok(project);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectFetchDto>> listAllProjects(
            @RequestParam(required = false) Long user,
            @RequestParam(required = false) Long methodology,
            @RequestParam(required = false) Long license,
            @RequestParam() Integer limit,
            @RequestParam() Integer page) {
        if (limit <= 0 || page < 0) {
            return ResponseEntity.badRequest().body(Page.empty());
        }
        Pageable pageable = PageRequest.of(page, limit);
        Page<ProjectFetchDto> projects = projectFacade.fetchAllProjects(user, methodology, license, pageable);
        return ResponseEntity.ok(projects);
    }
}
