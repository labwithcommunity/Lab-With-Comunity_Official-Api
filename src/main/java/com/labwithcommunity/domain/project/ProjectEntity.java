package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.tag.dto.query.AssignedTagQueryDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@Data
class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String name;
    private String description;
    private LocalDateTime created;
    private String website;
    private String wiki;
    private String tracking;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private UserQueryDto creatorid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "methodology_id")
    @EqualsAndHashCode.Exclude
    private MethodologyEntity methodology;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "licence_id")
    @EqualsAndHashCode.Exclude
    private LicenceEntity licence;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<UserQueryDto> participants = new HashSet<>();

    @OneToOne(mappedBy = "project")
    @EqualsAndHashCode.Exclude
    private RatingEntity ratingEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private List<AssignedTagQueryDto> tags = new ArrayList<>();

    public ProjectEntity(String name, String description,
                         MethodologyEntity methodology,
                         LicenceEntity licence,
                         String website, String wiki,
                         String tracking, UserQueryDto creator) {
        this.name = name;
        this.description = description;
        this.website = website;
        this.wiki = wiki;
        this.tracking = tracking;
        this.methodology = methodology;
        this.licence = licence;
        this.creatorid = creator;
        this.created = LocalDateTime.now();
    }
}
