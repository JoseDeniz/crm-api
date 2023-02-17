package com.crm.application;

public record CreateUserRequest(String username, String password, String... roles) {
}
