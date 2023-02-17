package com.crm.persistence.orm.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.crm.persistence.orm.repositories")
@EntityScan("com.crm.persistence.orm.entity")
public class OrmConfiguration {
}
