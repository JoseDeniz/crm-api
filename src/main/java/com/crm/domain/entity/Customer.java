package com.crm.domain.entity;

public class Customer {
    private final String id;
    private final String name;
    private final String surname;
    private final String profileImageUrl;
    private final String createdBy;
    private final String lastUpdatedBy;

    public Customer(String id, String name, String surname, String profileImageUrl, String createdBy, String lastUpdatedBy) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profileImageUrl = profileImageUrl;
        this.createdBy = createdBy;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
}
