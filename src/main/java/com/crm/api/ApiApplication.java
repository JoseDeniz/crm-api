package com.crm.api;

import com.crm.application.CreateCustomerRequest;
import com.crm.application.CreateUserRequest;
import com.crm.application.CustomerService;
import com.crm.application.UserService;
import com.crm.persistence.orm.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ApiApplication {
	@Autowired
	CustomerService customerService;
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		var adminUsername = "admin@email.com";
		var adminUser = new CreateUserRequest(adminUsername, "adminSecretPassword", UserRole.ADMIN.getValue());
		var user2Username = "user1@email.com";
		var user2 = new CreateUserRequest(user2Username, "secretPassword1", UserRole.USER.getValue());
		userService.save(adminUser);
		userService.save(user2);

		customerService.save(new CreateCustomerRequest("Customer1", "Surname1", "anyProfileImage1"), adminUsername);
		customerService.save(new CreateCustomerRequest("Customer2", "Surname2", "anyProfileImage2"), user2Username);
	}
}
