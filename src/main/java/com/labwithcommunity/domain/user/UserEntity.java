package com.labwithcommunity.domain.user;

import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "Users")
class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Set<UserRoles> roles;

    public UserEntity( String password, String nickname, String email) {
        this.username = nickname;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.roles = new HashSet<>();
    }
}
