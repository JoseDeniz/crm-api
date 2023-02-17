package com.crm.api.infrastructure.security;

import com.crm.domain.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ApplicationUserRepository {

    private final UserRepository userRepository;

    public ApplicationUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApplicationUser findByUsername(String username) {
        var user = userRepository.findByUsername(username);
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles()),
                user.isEnabled()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(String[] roles) {
        return Arrays.stream(roles)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
