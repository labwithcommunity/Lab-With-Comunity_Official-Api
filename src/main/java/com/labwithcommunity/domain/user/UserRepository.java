package com.labwithcommunity.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface UserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByNickname(String nickanem);
}
