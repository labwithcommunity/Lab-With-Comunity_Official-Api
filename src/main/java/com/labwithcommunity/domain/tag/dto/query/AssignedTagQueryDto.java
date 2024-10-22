package com.labwithcommunity.domain.tag.dto.query;

import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity @Table(name = "assignedtags")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class AssignedTagQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long assignedId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "assigned_tags",
            joinColumns = @JoinColumn(name = "assigned_tag_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @EqualsAndHashCode.Exclude
    private List<TagQueryDto> tags;

    @ManyToOne(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private ProjectQueryDto project;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private UserQueryDto assignerId;

    public AssignedTagQueryDto(List<TagQueryDto> tags, ProjectQueryDto project, UserQueryDto assignerId) {
        this.tags = tags;
        this.project = project;
        this.assignerId = assignerId;
    }
}
