package com.crm.application;

import com.crm.domain.entity.User;
import com.crm.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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

    @Test
    void should_not_save_a_user_if_already_exists_the_username() {
        when(passwordEncoder.encode("anyPassword")).thenReturn("encodedPassword");
        when(userRepository.findByUsername("anyUsername")).thenReturn(mock(User.class));

        assertThatExceptionOfType(UserAlreadyExistsException.class).isThrownBy(() -> {
            userService.save(
                    new CreateUserRequest("anyUsername", "anyPassword", "ROLE_USER")
            );
        });
        verify(userRepository, never()).save(any());
    }
}