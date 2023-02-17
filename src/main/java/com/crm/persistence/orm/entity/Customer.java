package com.crm.persistence.orm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "profile_image_url")
    private String profileImageUrl;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Customer setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public Customer setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Customer setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Customer setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }
}
