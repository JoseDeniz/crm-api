package com.crm.persistence.orm.repositories;

import com.crm.domain.entity.Customer;
import com.crm.domain.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ORMCustomerRepository implements CustomerRepository {

    private final JpaCustomerRepository customerRepository;

    @Autowired
    public ORMCustomerRepository(JpaCustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void save(Customer customer) {
        var customerToBePersisted = new com.crm.persistence.orm.entity.Customer();
        customerToBePersisted
            .setId(customer.getId())
            .setName(customer.getName())
            .setSurname(customer.getSurname())
            .setProfileImageUrl(customer.getProfileImageUrl())
            .setCreatedBy(customer.getCreatedBy())
            .setLastUpdatedBy(customer.getLastUpdatedBy());
        customerRepository.save(customerToBePersisted);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(
                    customer -> new Customer(
                        customer.getId(),
                        customer.getName(),
                        customer.getSurname(),
                        customer.getProfileImageUrl(),
                        customer.getCreatedBy(),
                        customer.getLastUpdatedBy()
                ))
                .toList();
    }

    @Override
    public Customer findById(String id) {
        var customer = customerRepository.findById(id).orElseThrow();
        return new Customer(
            customer.getId(),
            customer.getName(),
            customer.getSurname(),
            customer.getProfileImageUrl(),
            customer.getCreatedBy(),
            customer.getLastUpdatedBy()
        );
    }
}