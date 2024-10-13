package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.dto.GetLoggedUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String nickname);

    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT new com.labwithcommunity.domain.user.dto.GetLoggedUserDto(u.username, u.password) FROM UserEntity u WHERE u.username = :username")
    Optional<GetLoggedUserDto> findUsernameAndPasswordByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByNickname(String username);

    Optional<UserEntity> findByEmail(String email);
}
