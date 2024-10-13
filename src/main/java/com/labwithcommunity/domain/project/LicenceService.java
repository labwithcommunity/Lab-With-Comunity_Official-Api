package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.project.dto.licence.LicenceCreateDto;
import com.labwithcommunity.domain.project.exception.licence.LicenceAlreadyExistException;
import com.labwithcommunity.domain.project.exception.licence.LicenceExceptionMessages;
import com.labwithcommunity.domain.project.exception.licence.LicenceNotFoundException;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Server
class LicenceService {

    private final LicenceRepository licenceRepository;

    LicenceEntity getLicenceById(int id) {
        return licenceRepository.findById(id)
                .orElseThrow(() -> new LicenceNotFoundException(
                        LicenceExceptionMessages.LICENCE_NOT_FOUND_BY_GIVEN_ID.getMessage()));
    }

    LicenceCreateDto addLicence(LicenceCreateDto licenceCreateDto) {
        checkIfLicenceNameExistsAndThrow(licenceCreateDto.name(), licenceCreateDto.symbol());
        LicenceEntity newLicence = new LicenceEntity(
                licenceCreateDto.name(),
                licenceCreateDto.symbol(),
                licenceCreateDto.description()
        );
        licenceRepository.save(newLicence);
        return licenceCreateDto;
    }

    void checkIfLicenceNameExistsAndThrow(String licenceName, String symbol) {
        if (isLicenceNameExist(licenceName) || isLicenceSymbolExist(symbol)) {
            throw new LicenceAlreadyExistException(LicenceExceptionMessages.LICENCE_ALREADY_EXISTS.getMessage());
        }
    }

    boolean isLicenceNameExist(String licenceName) {
        return licenceRepository.existsByName(licenceName);
    }

    boolean isLicenceSymbolExist(String symbol) {
        return licenceRepository.existsBySymbol(symbol);
    }
}
