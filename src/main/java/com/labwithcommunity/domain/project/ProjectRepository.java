package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<List<ProjectEntity>> findAllByOwner(UserQueryDto owner);

    @Query("SELECT p FROM ProjectEntity p JOIN p.participants part WHERE part = :user")
    Optional<List<ProjectEntity>> findProjectsByParticipant(UserQueryDto user);
    ProjectEntity findByTitle(String title);
    boolean existsByTitle(String title);
    boolean existsByParticipantsContaining(UserQueryDto user);
}
