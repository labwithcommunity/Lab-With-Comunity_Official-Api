package com.labwithcommunity.domain.project;

import org.springframework.data.jpa.repository.JpaRepository;


interface LicenceRepository extends JpaRepository<LicenceEntity, Integer> {
    boolean existsByName(String name);
    boolean existsBySymbol(String symbol);
}
