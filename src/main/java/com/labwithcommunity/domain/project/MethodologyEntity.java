package com.labwithcommunity.domain.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "methodologys")
@Getter @Setter
@NoArgsConstructor
class MethodologyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int methodologyId;
    private String methodologyName;

    public MethodologyEntity(String methodologyName) {
        this.methodologyName = methodologyName;
    }
}
