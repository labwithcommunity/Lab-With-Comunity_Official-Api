package com.labwithcommunity.domain.technology;

import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import com.labwithcommunity.domain.technology.dto.query.TechnologyQueryDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

@Entity @Table(name = "userd_technologies")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
class UsedTechnologiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UsedTechnologyId;

    @ManyToOne()
    @EqualsAndHashCode.Exclude
    private ProjectQueryDto projectId;

    @ManyToOne(fetch = FetchType.LAZY) // Użycie FetchType.LAZY
    @JoinColumn(name = "technology_id_technology_id", nullable = false) // Dostosuj nazwę kolumny zgodnie z Twoimi potrzebami
    @EqualsAndHashCode.Exclude
    private TechnologyEntity technologyId;

    @EqualsAndHashCode.Exclude
    private int level;

    public UsedTechnologiesEntity(ProjectQueryDto projectId, TechnologyEntity technologyId, int level) {
        this.projectId = projectId;
        this.technologyId = technologyId;
        this.level = level;
    }
}
