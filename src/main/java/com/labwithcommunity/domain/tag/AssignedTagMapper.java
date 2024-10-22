package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.tag.dto.query.AssignedTagQueryDto;

import java.util.stream.Collectors;

class AssignedTagMapper {

    static AssignedTagQueryDto mapToAssignedQuery(AssignedTagEntity assignedTagEntity) {
        return new AssignedTagQueryDto(
                assignedTagEntity.getTags().stream()
                        .map(tag -> TagMapper.mapToQueryEntity(tag, tag.getUser()))
                        .collect(Collectors.toList()),
                assignedTagEntity.getProject(),
                assignedTagEntity.getAssignerId()
        );
    }

}
