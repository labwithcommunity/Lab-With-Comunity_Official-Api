package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<List<ProjectEntity>> findAllByCreatorid(UserQueryDto creatorId);

    @Query("SELECT p FROM ProjectEntity p JOIN p.participants part WHERE part = :user")
    Optional<List<ProjectEntity>> findProjectsByParticipant(UserQueryDto user);

    Optional<ProjectEntity> findById(Long Id);

    boolean existsByName(String title);

    boolean existsByParticipantsContainingAndId(UserQueryDto user, Long id);

    boolean existsById(Long id);

    @Query("SELECT p FROM ProjectEntity p " +
            "WHERE (:creatorid IS NULL OR p.creatorid.nickname = :creatorid) " +
            "AND (:methodology IS NULL OR p.methodology.id = :methodology) " +
            "AND (:license IS NULL OR p.licence.id = :license)")
    Page<ProjectEntity> findAllByFilters(@Param("creatorid") String creatorid,
                                         @Param("methodology") Long methodology,
                                         @Param("license") Long license,
                                         Pageable pageable);

}