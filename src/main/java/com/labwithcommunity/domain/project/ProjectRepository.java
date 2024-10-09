package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<List<ProjectEntity>> findAllByCreatorid(UUID creatorId);

    @Query("SELECT p FROM ProjectEntity p JOIN p.participants part WHERE part = :user")
    Optional<List<ProjectEntity>> findProjectsByParticipant(UserQueryDto user);
    Optional<ProjectEntity> findById(Long Id);
    boolean existsByName(String title);
    boolean existsByParticipantsContainingAndId(UserQueryDto user, Long id);
    boolean existsById(Long id);
}
