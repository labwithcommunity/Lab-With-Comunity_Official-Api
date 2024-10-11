package com.labwithcommunity.domain.project.dto.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labwithcommunity.domain.user.dto.query.UserQueryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "projects")
public class ProjectQueryDto {

    @Id
    private Long id;
    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private UserQueryDto owner;
}
