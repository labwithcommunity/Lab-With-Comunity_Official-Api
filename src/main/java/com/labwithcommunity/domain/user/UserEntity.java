package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.enums.UserMemberRoles;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private Set<UserMemberRoles> roles = new HashSet<>();
    @ElementCollection
    private Set<Technologies> technologies = new HashSet<>();



    public UserEntity(String nickname,String password,  String email, Set<Technologies> technologies) {
        this.username = nickname;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.technologies = technologies;
    }

}
