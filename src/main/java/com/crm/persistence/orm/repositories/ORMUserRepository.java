package com.crm.persistence.orm.repositories;

import com.crm.domain.entity.User;
import com.crm.domain.repositories.UserRepository;
import com.crm.persistence.orm.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ORMUserRepository implements UserRepository {

    private final JpaUserRepository userRepository;

    @Autowired
    public ORMUserRepository(JpaUserRepository customerRepository) {
        this.userRepository = customerRepository;
    }

    public void save(User user) {
        var userToBePersisted = new com.crm.persistence.orm.entity.User();
        userToBePersisted
            .setId(user.getId())
            .setUsername(user.getUsername())
            .setPassword(user.getPassword())
            .setRoles(user.getRoles())
            .setIsEnabled(user.isEnabled());
        userRepository.save(userToBePersisted);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return user.map(persistedUser -> new User(
                persistedUser.getId(),
                persistedUser.getUsername(),
                persistedUser.getPassword(),
                persistedUser.getIsEnabled(),
                persistedUser.getRoles().stream().map(UserRole::getValue).toArray(String[]::new)
            ));
        }
        return Optional.empty();
    }
}