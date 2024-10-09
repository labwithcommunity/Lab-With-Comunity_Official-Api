package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.MethodologyCreateDto;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class MethodologyService {

    private final MethodologyRepository methodologyRepository;

    MethodologyEntity getMethodologyById(int id) {

        //todo exception handling

        return methodologyRepository.findById(id).orElse(null);
    }

    MethodologyCreateDto createMethodology(MethodologyCreateDto methodologyCreateDto) {

        methodologyRepository.save(new MethodologyEntity(
                methodologyCreateDto.name()
        ));
        return methodologyCreateDto;
    }
}
