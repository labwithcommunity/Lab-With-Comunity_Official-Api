package com.labwithcommunity.domain.technology.dto.query;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "technologies")
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class TechnologyQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long technologyId;

    private String shortName;
    private String name;
    @EqualsAndHashCode.Exclude
    private String description;
}
