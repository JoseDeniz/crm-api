package com.crm.domain.repositories;

import com.crm.domain.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    void save(Customer customer);

    List<Customer> findAll();

    Customer findById(String id);
}
