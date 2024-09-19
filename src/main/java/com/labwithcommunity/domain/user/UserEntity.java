package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.enums.UserRoles;
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
    private String password;
    private String nickname;
    private String email;
    private Set<UserRoles> roles = new HashSet<>();


    public UserEntity(String password, String nickname, String email) {
        this.username = nickname;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
}
