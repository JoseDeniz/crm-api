package com.crm.application;

import java.io.Serializable;

public record CustomerDto(String id, String name, String surname, String profileImageUrl, String createdBy, String lastUpdatedBy) implements Serializable {
}
