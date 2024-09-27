package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    Optional<List<ProjectEntity>> findAllByOwner(UserQueryDto owner);

    @Query("SELECT p FROM ProjectEntity p WHERE p.owner = :user OR :user MEMBER OF p.participants")
    List<ProjectEntity> findProjectsByParticipantOrOwner(UserQueryDto user);



    ProjectEntity findByTitle(String title);

}
