package com.crm.api.controller;

import com.crm.api.infrastructure.security.ApplicationUser;
import com.crm.application.CreateCustomerRequest;
import com.crm.application.CustomerDto;
import com.crm.application.CustomerNotFoundException;
import com.crm.application.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/v1/customers")
    public List<CustomerDto> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/api/v1/customers/{id}")
    public ResponseEntity<CustomerDto> findById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(customerService.findById(id));
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/v1/customers/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        String username = ((ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return customerService.save(createCustomerRequest, username);
    }
}

