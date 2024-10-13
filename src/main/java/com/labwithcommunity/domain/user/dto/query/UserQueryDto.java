package com.labwithcommunity.domain.user.dto.query;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "users")
public class UserQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    //    private Set<UserMemberRoles> roles = new HashSet<>();

//    @EqualsAndHashCode.Exclude
//    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonBackReference
//    private List<ProjectQueryDto> ownedProjects = new ArrayList<>();
}
