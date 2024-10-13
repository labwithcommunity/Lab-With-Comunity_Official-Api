package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.methodology.MethodologyCreateDto;
import com.labwithcommunity.domain.project.exception.methodology.MethodologyExceptionMessages;
import com.labwithcommunity.domain.project.exception.methodology.MethodologyNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MethodologyService {

    private final MethodologyRepository methodologyRepository;

    MethodologyEntity getMethodologyById(int id) {
        return methodologyRepository.findById(id)
                .orElseThrow(()->new MethodologyNotFoundException(
                        MethodologyExceptionMessages.METHODOLOGY_NOT_FOUND.getMessage()));
    }

    MethodologyCreateDto createMethodology(MethodologyCreateDto methodologyCreateDto) {
        if (!methodologyRepository.existsByMethodologyName(methodologyCreateDto.methodologyName())){
            methodologyRepository.save(new MethodologyEntity(
                    methodologyCreateDto.methodologyName()
            ));
        }
        return methodologyCreateDto;
    }
}
