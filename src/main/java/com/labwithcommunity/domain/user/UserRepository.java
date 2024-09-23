package com.labwithcommunity.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByUsername(String nickname);
    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.technologies WHERE u.nickname = :nickname")
    Optional<UserEntity> findByUsername(String nickname);
}
