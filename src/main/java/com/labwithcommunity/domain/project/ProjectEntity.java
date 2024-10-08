package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "projects")
class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String github;
    private Double rating;
    private String description;

    //todo
    /*
    "id": 1,
    "name": "Projekt 1",
    "description": "Opis projektu 1",
    "created": "2022-01-01 12:00:00",
    "creator": "username",
    "methodology": "Extreme Programming",
    "license": "MIT",
    "website": "https://example.com/project1",
    "wiki": "https://example.com/project1/wiki",
    "tracking": "https://example.com/project1/tracking"
     */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private UserQueryDto owner;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<UserQueryDto> participants = new HashSet<>();

    public ProjectEntity(String title, String github, String description, UserQueryDto owner) {
        this.title = title;
        this.github = github;
        this.description = description;
        this.rating = 0.0;
        this.owner = owner;
    }
}
