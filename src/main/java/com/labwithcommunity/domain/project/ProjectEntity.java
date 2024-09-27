package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    private Double rating;
    private String description;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserQueryDto owner;
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "project_participants", joinColumns = @JoinColumn(name = "project_id"))
//    @Column(name = "participant")
    @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Set<UserQueryDto> participants = new HashSet<>();

    public ProjectEntity(String description, String title) {
        this.description = description;
        this.title = title;
    }


//    // Tutaj możesz dodać listę ról projektowych
//    @ElementCollection
//    private Set<ProjectRole> projectRoles = new HashSet<>();
}
