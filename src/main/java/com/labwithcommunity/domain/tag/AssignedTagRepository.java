package com.labwithcommunity.domain.tag;

import org.springframework.data.jpa.repository.JpaRepository;

interface AssignedTagRepository extends JpaRepository<AssignedTagEntity, Long> {

    AssignedTagEntity findByProjectProjectId(Long projectId);

}
