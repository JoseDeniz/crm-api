package com.crm.application;

import com.crm.domain.entity.User;
import com.crm.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String save(CreateUserRequest createUserRequest) {
        var persistedUser = userRepository.findByUsername(createUserRequest.username());
        if (persistedUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        var newUserId = UUID.randomUUID().toString();
        this.userRepository.save(
            new User(
                    newUserId,
                    createUserRequest.username(),
                    passwordEncoder.encode(createUserRequest.password()),
                    true,
                    createUserRequest.roles()
            )
        );
        return newUserId;
    }
}
