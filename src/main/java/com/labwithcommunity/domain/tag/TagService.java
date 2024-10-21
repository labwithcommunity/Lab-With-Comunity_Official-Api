package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.project.dto.project.ProjectCreateDto;
import com.labwithcommunity.domain.tag.dto.TagCreateDto;
import com.labwithcommunity.domain.tag.exception.TagExceptionMessages;
import com.labwithcommunity.domain.tag.exception.TagNotFoundException;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public List<String> addTagsInChain(ProjectCreateDto projectCreateDTO, UserQueryDto creator) {
        List<String> tagNames = new ArrayList<>();
        for (TagCreateDto tagCreateDto : projectCreateDTO.tags()) {
            Optional<TagEntity> tagEntity;
            tagEntity = tagRepository.findByName(tagCreateDto.name());
            if (tagEntity.isEmpty()) {
                tagRepository.save(TagMapper.mapToEntity(tagCreateDto, creator));
            }
            tagNames.add(tagCreateDto.name());
        }
        return tagNames;
    }


    @Transactional
    public List<TagEntity> findTagsByNameIn(List<String> tagNames) {
        List<TagEntity> tags = tagRepository.findByNameIn(tagNames);
        if (tags.size() != tagNames.size()) {
            throw new TagNotFoundException(TagExceptionMessages.TAG_NOT_FOUND.getMessage());
        }
        return tags;
    }
}
