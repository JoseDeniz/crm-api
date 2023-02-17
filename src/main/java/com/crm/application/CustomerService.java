package com.crm.application;

import com.crm.domain.entity.Customer;
import com.crm.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerDto(
                        customer.getId(),
                        customer.getName(),
                        customer.getSurname(),
                        customer.getProfileImageUrl(),
                        customer.getCreatedBy(),
                        customer.getLastUpdatedBy()
                ))
                .toList();
    }

    public CustomerDto findById(String id) {
        try {
            var customer = customerRepository.findById(id);
            return new CustomerDto(
                    customer.getId(),
                    customer.getName(),
                    customer.getSurname(),
                    customer.getProfileImageUrl(),
                    customer.getCreatedBy(),
                    customer.getLastUpdatedBy()
            );
        } catch (Exception e) {
            throw new CustomerNotFoundException();
        }
    }

    public String save(CreateCustomerRequest createCustomerRequest, String creatorUsername) {
        var newCustomerId = UUID.randomUUID().toString();
        customerRepository.save(new Customer(
                newCustomerId,
                createCustomerRequest.name(),
                createCustomerRequest.surname(),
                createCustomerRequest.profileImageUrl(),
                creatorUsername,
                creatorUsername
        ));
        return newCustomerId;
    }
}
