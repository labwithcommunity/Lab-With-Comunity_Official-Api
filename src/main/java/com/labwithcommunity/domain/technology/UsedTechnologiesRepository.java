package com.labwithcommunity.domain.technology;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UsedTechnologiesRepository extends JpaRepository<UsedTechnologiesEntity, Long> {
}
