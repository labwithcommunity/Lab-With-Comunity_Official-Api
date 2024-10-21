package com.labwithcommunity.domain.technology;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "technologies")
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
class TechnologyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long technologyId;

    private String shortName;
    private String name;
    @EqualsAndHashCode.Exclude
    private String description;

    public TechnologyEntity(String shortName, String name, String description) {
        this.shortName = shortName;
        this.name = name;
        this.description = description;
    }
}
