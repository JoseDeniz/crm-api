package com.crm.api;

import com.crm.domain.repositories.CustomerRepository;
import com.crm.domain.repositories.UserRepository;
import com.crm.persistence.orm.repositories.ORMCustomerRepository;
import com.crm.persistence.orm.repositories.ORMUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
    "com.crm.persistence.orm",
    "com.crm.application",
    "com.crm.api.infrastructure.security"
})
public class ApplicationConfiguration {
    @Bean
    public CustomerRepository customerRepository(ORMCustomerRepository ormCustomerRepository) {
        return ormCustomerRepository;
    }

    @Bean
    public UserRepository userRepository(ORMUserRepository ormUserRepository) {
        return ormUserRepository;
    }
}
