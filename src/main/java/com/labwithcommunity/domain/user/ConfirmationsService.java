package com.labwithcommunity.domain.user;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConfirmationsService {

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
