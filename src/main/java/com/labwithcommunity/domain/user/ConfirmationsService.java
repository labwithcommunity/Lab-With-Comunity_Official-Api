package com.labwithcommunity.domain.user;

import java.time.LocalDateTime;

class ConfirmationsService {

    private ConfirmationsRepository confirmationsRepository;

    public ConfirmationsService(ConfirmationsRepository confirmationsRepository) {
        this.confirmationsRepository = confirmationsRepository;
    }

    void addConfirmation(UserEntity userEntity) {
        ConfirmationEntity confirmationEntity = new ConfirmationEntity();
        confirmationEntity.setCreationDate(LocalDateTime.now());
        confirmationEntity.setExpirationDate(confirmationEntity.getCreationDate().plusHours(8));
        confirmationEntity.setUserEntity(userEntity);
        confirmationsRepository.save(confirmationEntity);
    }
}
