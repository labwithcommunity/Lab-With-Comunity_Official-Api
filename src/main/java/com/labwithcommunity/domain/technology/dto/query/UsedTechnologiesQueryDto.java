package com.labwithcommunity.domain.technology.dto.query;

import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "userd_technologies")
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class UsedTechnologiesQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UsedTechnologyId;

    @ManyToOne()
    @EqualsAndHashCode.Exclude
    private ProjectQueryDto projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_id_technology_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private TechnologyQueryDto technologyId;

    @EqualsAndHashCode.Exclude
    private int level;
}
