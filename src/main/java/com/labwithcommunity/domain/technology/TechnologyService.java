package com.labwithcommunity.domain.technology;

import com.labwithcommunity.domain.technology.dto.TechnologyCreateDto;
import com.labwithcommunity.domain.technology.dto.TechnologyDto;
import com.labwithcommunity.domain.technology.exception.TechnologyAlreadyExistException;
import com.labwithcommunity.domain.technology.exception.TechnologyExceptionMessages;
import com.labwithcommunity.domain.technology.exception.TechnologyNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class TechnologyService {

    private final TechnologyRepository technologyRepository;

    TechnologyDto addTechnology(TechnologyCreateDto technologyCreateDto) {
        confirmIfExistByName(technologyCreateDto.name());
        TechnologyEntity technology = buildTechnologyEntity(technologyCreateDto);
        TechnologyEntity savedTechnology = technologyRepository.save(technology);
        return TechnologyMapper.mapToTechnologyDto(savedTechnology);
    }

    private TechnologyEntity buildTechnologyEntity(TechnologyCreateDto dto) {
        return new TechnologyEntity(dto.shortName(), dto.name(), dto.description());
    }

    void confirmIfExistByName(String name){
        if (isTechnologyExist(name)) {
            throw new TechnologyAlreadyExistException(
                    TechnologyExceptionMessages.TECHNOLOGY_ALREADY_EXIST.getMessage());
        }
    }

    boolean isTechnologyExist(String technologyName) {
        return technologyRepository.existsByName(technologyName);
    }

    TechnologyEntity findTechnologyById(long id){
        return technologyRepository.findById(id)
                .orElseThrow(()-> new TechnologyNotFoundException(
                        TechnologyExceptionMessages.TECHNOLOGY_NOT_FOUND.getMessage()));


    }
}
