package com.labwithcommunity.domain.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface TagRepository extends JpaRepository<TagEntity, Long> {
    boolean existsByName(String name);
    List<TagEntity> findByName(String name);
}
