package com.labwithcommunity.domain.user;

import com.labwithcommunity.domain.user.enums.ProgrammingLanguage;
import com.labwithcommunity.domain.user.enums.TechnologiesForProgrammingLanguage;
import com.labwithcommunity.domain.user.exception.UserExceptionMessages;
import com.labwithcommunity.domain.user.exception.UserTechnologyNotFoundException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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

    @EqualsAndHashCode.Exclude
    @ElementCollection
    private Set<Technologies> technologies = new HashSet<>();

    public UserEntity(String nickname, String password, String email) {
        this.username = nickname;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public void removeTechnologyFromUser(ProgrammingLanguage programmingLanguage,
                                         Set<TechnologiesForProgrammingLanguage> technologyToRemove) {
        Technologies technologies = this.technologies.stream()
                .filter(t -> t.getProgrammingLanguage().equals(programmingLanguage))
                .findFirst()
                .orElseThrow(() -> new UserTechnologyNotFoundException(UserExceptionMessages.TECHNOLOGY_NOT_FOUND.getMessage()));
        technologies.removeTechnology(technologyToRemove);
        isTechnologyEmpty(technologies);
    }

    private void isTechnologyEmpty(Technologies technologies) {
        if (technologies.isEmpty()) {
            this.technologies.remove(technologies);
        }
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
