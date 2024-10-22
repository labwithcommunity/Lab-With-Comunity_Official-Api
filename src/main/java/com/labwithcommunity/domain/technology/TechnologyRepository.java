package com.labwithcommunity.domain.technology;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TechnologyRepository extends JpaRepository<TechnologyEntity,Long> {
    boolean existsByName(String name);
}