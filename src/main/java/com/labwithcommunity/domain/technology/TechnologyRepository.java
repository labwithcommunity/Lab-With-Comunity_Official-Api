package com.labwithcommunity.domain.technology;

import org.springframework.data.jpa.repository.JpaRepository;

interface TechnologyRepository extends JpaRepository<TechnologyEntity,Long> {
    boolean existsByName(String name);
}
