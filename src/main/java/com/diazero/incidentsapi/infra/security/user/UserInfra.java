package com.diazero.incidentsapi.infra.security.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "user")
public class UserInfra implements UserDetails {
    @Id
    @Column(name = "id", nullable = false)
    @Access(AccessType.FIELD)
    private Long id;
    @Access(AccessType.FIELD)
    private String userName;
    @Access(AccessType.FIELD)
    @JsonIgnore
    private String password;
    @Access(AccessType.FIELD)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="role")
    private Set<Integer> roles = new HashSet<>();

    public UserInfra() {
        this.addRole(UserRoles.NORMAL);
    }
    public UserInfra(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole())).toList();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
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

    void setPassword(String password) {
        this.password=password;
    }

    public Set<UserRoles> getRoles() {
        return roles.stream().map(UserRoles::toEnum).collect(Collectors.toUnmodifiableSet());
    }

    public void addRole(UserRoles role) {
        this.roles.add(role.getCode());
    }
}
