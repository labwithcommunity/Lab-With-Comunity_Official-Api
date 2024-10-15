package com.labwithcommunity.domain.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface AssignedTagRepository extends JpaRepository<AssignedTagEntity, Long> {

    List<AssignedTagEntity> findAllByProjectProjectId(Long communityId);
}
