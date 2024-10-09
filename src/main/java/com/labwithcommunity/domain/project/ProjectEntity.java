package com.labwithcommunity.domain.project;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity @Table(name = "projects")
@NoArgsConstructor
@Data
class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created;
    private String website;
    private String wiki;
    private String tracking;
    private UUID creatorid;

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

    public ProjectEntity(String name,String description,
                         MethodologyEntity methodology,
                         LicenceEntity licence,
                          String website, String wiki,
                         String tracking,  UUID creator) {
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
