package com.crm.application;

import com.crm.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void should_save_a_user() {
        when(passwordEncoder.encode("anyPassword")).thenReturn("encodedPassword");

        var savedUserId = userService.save(
            new CreateUserRequest("anyUsername", "anyPassword", "ROLE_USER")
        );

        verify(userRepository, times(1))
            .save(argThat(user ->
                user.getId() != null
                && user.getUsername().equals("anyUsername")
                && user.getPassword().equals("encodedPassword")
            )
        );
        assertNotNull(savedUserId);
    }
}