package com.crm.domain.repositories;

import com.crm.domain.entity.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findByUsername(String username);
}
