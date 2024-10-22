package com.labwithcommunity.domain.technology;

import com.labwithcommunity.domain.technology.dto.TechnologyDto;

class TechnologyMapper {

    static TechnologyDto mapToTechnologyDto(TechnologyEntity technology){
        return new TechnologyDto(technology.getName(), technology.getDescription());
    }
}