package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import com.labwithcommunity.domain.tag.dto.query.TagQueryDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity @Table(name = "assignedtags")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
class AssignedTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long assignedId;

    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TagQueryDto tag;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectQueryDto project;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private UserQueryDto assignerId;

    public AssignedTagEntity(TagQueryDto tag, ProjectQueryDto project, UserQueryDto assignerId) {
        this.tag = tag;
        this.project = project;
        this.assignerId = assignerId;
    }
}
