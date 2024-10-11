package com.labwithcommunity.domain.project.dto.project;

public record ProjectCreateDto(
        String name,
        String description,
        String website,
        String wiki,
        String tracking,
        int methodologyId,
        int licenceId

) {
}
