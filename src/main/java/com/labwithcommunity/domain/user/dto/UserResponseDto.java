package com.labwithcommunity.domain.user.dto;


import com.labwithcommunity.domain.project.dto.query.ProjectQueryDto;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record UserResponseDto(
        String username,
        String nickname,
        String email
) {
}
