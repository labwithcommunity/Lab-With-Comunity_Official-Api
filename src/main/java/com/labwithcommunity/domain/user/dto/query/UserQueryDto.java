package com.labwithcommunity.domain.user.dto.query;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.labwithcommunity.domain.project.dto.query.ProjectQueryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "users")
public class UserQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;
    private String email;
    //    private Set<UserMemberRoles> roles = new HashSet<>();

//    @EqualsAndHashCode.Exclude
//    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonBackReference
//    private List<ProjectQueryDto> ownedProjects = new ArrayList<>();
}
