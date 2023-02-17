package com.crm.persistence.orm.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "enabled")
    private Boolean isEnabled;
    @Column(name = "roles")
    private String roles;

    @Transient
    private final String ROLES_DELIMITER = " ";

    public User() {
        this.username = "";
        this.password = "";
        this.isEnabled = true;
        this.roles = UserRole.USER.getValue();
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<UserRole> getRoles() {
        return Arrays.stream(this.roles.split(ROLES_DELIMITER))
                .map(UserRole::fromValue).collect(toList());
    }

    public User setRoles(UserRole... roles) {
        this.roles = Arrays.stream(roles)
                .map(UserRole::getValue)
                .collect(Collectors.joining(ROLES_DELIMITER));
        return this;
    }

    public User setRoles(String[] roles) {
        this.roles = String.join(ROLES_DELIMITER, roles);
        return this;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public User setIsEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }
}
