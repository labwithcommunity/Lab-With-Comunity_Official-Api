package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.LicenceCreateDto;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Server
class LicenceService {

    private final LicenceRepository licenceRepository;

    LicenceEntity getLicenceById(int id) {

        //todo exception handling

        return licenceRepository.findById(id).orElseThrow(null);
    }

    LicenceCreateDto addLicence(LicenceCreateDto licenceCreateDto) {
        //todo handling

        licenceRepository.save(new LicenceEntity(
                licenceCreateDto.name(),
                licenceCreateDto.symbol(),
                licenceCreateDto.description()
        ));
        return licenceCreateDto;

    }
}
