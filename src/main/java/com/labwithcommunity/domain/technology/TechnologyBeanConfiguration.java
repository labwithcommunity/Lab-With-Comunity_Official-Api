package com.labwithcommunity.domain.technology;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TechnologyBeanConfiguration {


    @Bean
    TechnologyService technologyService(TechnologyRepository repository) {
        return new TechnologyService(repository);
    }

    @Bean
    UsedTechnologiesService usedTechnologiesService(UsedTechnologiesRepository repository,TechnologyService technologyService) {
        return new UsedTechnologiesService(repository,technologyService);
    }

    @Bean
    TechnologyFacade technologyFacade(UsedTechnologiesService usedTechnologiesService) {
        return new TechnologyFacade(usedTechnologiesService);
    }
}
