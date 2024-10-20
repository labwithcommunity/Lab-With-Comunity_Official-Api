package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity @Table(name = "assignedtags")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
class AssignedTagEntity {

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
    private List<TagEntity> tags;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectQueryDto project;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private UserQueryDto assignerId;

    public AssignedTagEntity(List<TagEntity> tags, ProjectQueryDto project, UserQueryDto assignerId) {
        this.tags = tags;
        this.project = project;
        this.assignerId = assignerId;
    }
}
