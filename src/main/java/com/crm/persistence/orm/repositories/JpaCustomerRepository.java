package com.crm.persistence.orm.repositories;

import com.crm.persistence.orm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<Customer, String> {
}
