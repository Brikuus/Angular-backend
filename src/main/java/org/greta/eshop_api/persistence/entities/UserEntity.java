package org.greta.eshop_api.persistence.entities;

import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.file.FileStore;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users") // 👈 'user' est un mot réservé SQL
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    /* 🔐 Méthodes UserDetails (utilisées par Spring Security) */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> "ROLE_" + role.name());
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return email; // 👈 l'email sert d'identifiant
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    public String getEmail() {
        return this.email;
    }

    public Role getRole() {
        return this.role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(@Nullable String encode) {
        this.password = encode;
    }

    public Long getId() {
        return this.id;
    }

    // Constructor, getters & setters
}
