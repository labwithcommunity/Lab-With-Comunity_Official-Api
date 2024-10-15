package com.labwithcommunity.domain.tag.dto.query;

import com.labwithcommunity.domain.project.dto.project.query.ProjectQueryDto;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity @Table(name = "assignedtags")
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class AssignedTagQueryDto {

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

    public AssignedTagQueryDto(TagQueryDto tag, ProjectQueryDto project, UserQueryDto assignerId) {
        this.tag = tag;
        this.project = project;
        this.assignerId = assignerId;
    }
}
