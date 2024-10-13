package com.labwithcommunity.domain.project.dto.project;

import java.time.LocalDateTime;

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
        String licence

) {

}
