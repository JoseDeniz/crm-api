package com.crm.persistence.orm.repositories;

import com.crm.persistence.orm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
