package com.labwithcommunity.domain.project;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "ratings")
@NoArgsConstructor
@Getter @Setter
class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ratingId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    private ProjectEntity project;
    private int rating;

    public RatingEntity(ProjectEntity project, int rating) {
        this.project = project;
        this.rating = rating;
    }
}
