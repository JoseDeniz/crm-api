package com.crm.domain.repositories;

import com.crm.domain.entity.User;

public interface UserRepository {
    void save(User user);

    User findByUsername(String username);
}
