package com.labwithcommunity.domain.project.dto.project;

import com.labwithcommunity.domain.tag.dto.query.AssignedTagQueryDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;

import java.time.LocalDateTime;
import java.util.List;

public record ProjectFetchDto(
        Long id,
        String name,
        String description,
        LocalDateTime created,
        String creator,
        String website,
        String wiki,
        String tracking,
        String methodology,
        String licence,
        List<String>tags

) {

}
