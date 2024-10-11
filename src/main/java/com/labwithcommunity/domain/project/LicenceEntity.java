package com.labwithcommunity.domain.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "licences")
@Getter @Setter
@NoArgsConstructor
class LicenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int licenceId;
    private String symbol;
    private String name;
    private String description;

    public LicenceEntity(String symbol, String name, String description) {
        this.symbol = symbol;
        this.name = name;
        this.description = description;
    }
}
