package com.labwithcommunity.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByNickname(String nickname);
    Optional<UserEntity> findByNickname(String nickname);
}
