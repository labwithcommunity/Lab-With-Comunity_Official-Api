package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.enums.UserMemberRoles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String nickname;
    private String password;
    private String email;
//    private Set<UserMemberRoles> roles = new HashSet<>();
    private String role;
    @ElementCollection
    private Set<Technologies> technologies = new HashSet<>();



    public UserEntity(String nickname,String password,  String email, Set<Technologies> technologies) {
        this.username = nickname;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.technologies = technologies;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
