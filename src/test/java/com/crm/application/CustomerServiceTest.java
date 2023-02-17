package com.crm.application;

import com.crm.domain.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    void should_save_a_customer() {
        var savedCustomerId = customerService.save(
            new CreateCustomerRequest("anyName", "anySurname", "anyProfileImageUrl"),
            "anyCreatorUsername"
        );

        verify(customerRepository, times(1))
            .save(argThat(customer ->
                customer.getId() != null
                && customer.getName().equals("anyName")
                && customer.getSurname().equals("anySurname")
                && customer.getProfileImageUrl().equals("anyProfileImageUrl")
                && customer.getCreatedBy().equals("anyCreatorUsername")
                && customer.getLastUpdatedBy().equals("anyCreatorUsername")
            )
        );
        assertNotNull(savedCustomerId);
    }
}