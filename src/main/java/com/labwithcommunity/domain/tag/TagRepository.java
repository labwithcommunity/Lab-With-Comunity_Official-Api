package com.labwithcommunity.domain.tag;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface TagRepository extends JpaRepository<TagEntity, Long> {
    boolean existsByName(String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT t FROM TagEntity t WHERE t.name = :name")
    Optional<TagEntity> findByName(String name);

    List<TagEntity> findByNameIn(List<String> tagNames);

}
