package com.labwithcommunity.domain.tag;

import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "tags")
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String name;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserQueryDto user;

    public TagEntity(String name, UserQueryDto user) {
        this.name = name;
        this.user = user;
    }
}
