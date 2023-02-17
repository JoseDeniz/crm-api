package com.crm.api.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final ApplicationUserRepository applicationUserRepository;

    @Autowired
    public ApplicationUserDetailsService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public ApplicationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        var applicationUser = applicationUserRepository.findByUsername(username);

        if (applicationUser == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return applicationUser;
        }
    }
}
