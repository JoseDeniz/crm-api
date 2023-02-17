package com.crm.domain.entity;

public class User {
    private final String id;
    private final String username;
    private final String password;
    private final String[] roles;
    private final boolean isEnabled;

    public User(String id, String username, String password, boolean isEnabled, String... roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.isEnabled = isEnabled;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String[] getRoles() {
        return roles;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
