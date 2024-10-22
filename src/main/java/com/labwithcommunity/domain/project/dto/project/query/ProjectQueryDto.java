package com.labwithcommunity.domain.project.dto.project.query;


import com.labwithcommunity.domain.tag.dto.query.AssignedTagQueryDto;
import com.labwithcommunity.domain.technology.dto.query.UsedTechnologiesQueryDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "projects")
public class ProjectQueryDto {

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
    private UserQueryDto owner;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<UserQueryDto> participants = new HashSet<>();

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE,orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private List<AssignedTagQueryDto> tags = new ArrayList<>();

    @OneToMany(mappedBy = "projectId",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE,orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private List<UsedTechnologiesQueryDto> usedTechnologies = new ArrayList<>();

    public ProjectQueryDto(Long projectId, String name, String description, UserQueryDto creatorid) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.owner = creatorid;
    }
}
