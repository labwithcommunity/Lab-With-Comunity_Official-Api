package com.labwithcommunity.domain.technology;

import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "userd_technologies")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
class UsedTechnologiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UsedTechnologyId;

    @ManyToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private ProjectQueryDto projectId;

    @OneToOne(cascade = CascadeType.ALL)
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
