package com.crm.persistence.orm.entity;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public static UserRole fromValue(String value) {
        if (value.equals(ADMIN.getValue())) return ADMIN;
        return USER;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
