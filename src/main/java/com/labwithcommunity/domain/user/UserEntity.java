package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.project.dto.query.ProjectQueryDto;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@NoArgsConstructor
@Data
@Table(name = "users")
@AllArgsConstructor
@Builder
class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private boolean isAdmin;
    private boolean isApproved;
    private boolean isActive;
    private LocalDateTime lastLogin;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private ConfirmationEntity confirmationEntity;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProjectQueryDto> ownedProjects = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (isAdmin) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
