package com.labwithcommunity.domain.tag.dto.query;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Table(name = "tags",uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class TagQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String name;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserQueryDto user;

    public TagQueryDto(String name, UserQueryDto user) {
        this.name = name;
        this.user = user;
    }
}
