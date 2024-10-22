package com.labwithcommunity.domain.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AssignedTagRepository extends JpaRepository<AssignedTagEntity, Long> {

    AssignedTagEntity findByProjectProjectId(Long projectId);
    boolean existsByProjectProjectId(Long projectId);

}
