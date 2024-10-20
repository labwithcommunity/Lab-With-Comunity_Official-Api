package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.tag.dto.TagCreateDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;

class TagMapper {

    static TagEntity mapToEntity(TagCreateDto tagCreateDto,
                                      UserQueryDto userQueryDto) {
        return new TagEntity(tagCreateDto.name(),
                                 userQueryDto);
    }

    static TagQueryDto mapToQueryEntity(TagEntity tagEntity,
                                        UserQueryDto userQueryDto) {
        return new TagQueryDto(tagEntity.getName(),
                userQueryDto);
    }

}
