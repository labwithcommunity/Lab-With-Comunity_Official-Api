package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.user.UserFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TagBeanConfiguration {

    @Bean
    TagService tagService(TagRepository tagRepository, UserFacade userFacade) {
        return new TagService(tagRepository,userFacade);
    }

    @Bean
    TagFacade tagFacade(TagService tagService,AssignedTagRepository assignedTagRepository) {
        AssignedTagService assignedTagService = new AssignedTagService(assignedTagRepository);
        return new TagFacade(tagService, assignedTagService);
    }
}
