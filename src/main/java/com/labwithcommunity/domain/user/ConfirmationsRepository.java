package com.labwithcommunity.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface ConfirmationsRepository extends JpaRepository<ConfirmationEntity, Long> {
}
