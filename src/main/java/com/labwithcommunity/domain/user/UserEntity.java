package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.project.dto.query.ProjectQueryDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    private boolean isLocked;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;


//    @EqualsAndHashCode.Exclude
//    @ElementCollection
//    private Set<Technologies> technologies = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProjectQueryDto> ownedProjects = new ArrayList<>();

    public UserEntity(String nickname, String password, String email) {
        this.username = nickname;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

//    public void removeTechnologyFromUser(ProgrammingLanguage programmingLanguage,
//                                         Set<TechnologiesForProgrammingLanguage> technologyToRemove) {
//        Technologies technologies = this.technologies.stream()
//                .filter(t -> t.getProgrammingLanguage().equals(programmingLanguage))
//                .findFirst()
//                .orElseThrow(() -> new UserTechnologyNotFoundException(UserExceptionMessages.TECHNOLOGY_NOT_FOUND.getMessage()));
//        technologies.removeTechnology(technologyToRemove);
//        isTechnologyEmpty(technologies);
//    }
//
//    private void isTechnologyEmpty(Technologies technologies) {
//        if (technologies.isEmpty()) {
//            this.technologies.remove(technologies);
//        }
//    }


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
