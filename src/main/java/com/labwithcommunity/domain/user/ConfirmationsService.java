package com.labwithcommunity.domain.user;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
class ConfirmationsService {

    private final ConfirmationsRepository confirmationsRepository;

    void addConfirmation(UserEntity userEntity) {
        ConfirmationEntity confirmationEntity = new ConfirmationEntity();
        confirmationEntity.setCreationDate(LocalDateTime.now());
        confirmationEntity.setExpirationDate(confirmationEntity.getCreationDate().plusHours(8));
        confirmationEntity.setUserEntity(userEntity);
        confirmationsRepository.save(confirmationEntity);
    }
}
