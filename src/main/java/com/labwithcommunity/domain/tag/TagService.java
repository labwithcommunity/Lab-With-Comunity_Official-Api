package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.tag.dto.TagCreateDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;
import com.labwithcommunity.domain.user.UserFacade;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
class TagService {

    private final TagRepository tagRepository;
    private final UserFacade userFacade;

    public TagQueryDto createOrAssignTag(TagCreateDto tagCreateDto, UserQueryDto user) {
        List<TagEntity> existingTags = tagRepository.findByName(tagCreateDto.name());

        TagEntity savedTag;
        if (!existingTags.isEmpty()) {
            // Możesz zdecydować, jak obsłużyć przypadek z wieloma wynikami
            savedTag = existingTags.get(0); // lub inna logika, np. wybranie konkretnego
            log.info("Tag already exists, assigning: {}", savedTag.getName());
        } else {
            savedTag = tagRepository.save(TagMapper.mapToEntity(tagCreateDto, user));
            log.info("Tag created: {}", savedTag.getName());
        }

        return TagMapper.mapToEntity(savedTag, savedTag.getUser());
    }


    boolean isExistByTagName(String name) {
        return tagRepository.existsByName(name);
    }

    TagEntity findById(Long id) {
        return tagRepository.findById(id).orElseThrow();
    }

}
