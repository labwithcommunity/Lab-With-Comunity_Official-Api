package com.labwithcommunity.domain.project.dto.project;

import com.labwithcommunity.domain.tag.dto.TagCreateDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record ProjectCreateDto(
        String name,
        String description,
        String website,
        String wiki,
        String tracking,
        int methodologyId,
        @NotNull
        @Positive
        int licenceId,
        List<TagCreateDto> tags

) {
}
