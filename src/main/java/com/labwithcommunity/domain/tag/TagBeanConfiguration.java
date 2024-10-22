package com.labwithcommunity.domain.tag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TagBeanConfiguration {

    @Bean
    AssignedTagService assignedTagService(AssignedTagRepository assignedTagRepository, TagService tagService) {
        return new AssignedTagService(assignedTagRepository, tagService);
    }

    @Bean
    TagService tagService(TagRepository tagRepository) {
        return new TagService(tagRepository);
    }

    @Bean
    TagFacade tagFacade(TagService tagService,AssignedTagService assignedTagRepository) {
        return new TagFacade(tagService, assignedTagRepository);
    }
}
