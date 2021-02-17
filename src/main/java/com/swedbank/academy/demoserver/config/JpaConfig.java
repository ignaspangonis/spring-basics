package com.swedbank.academy.demoserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.swedbank.academy.demoserver"})
public class JpaConfig {
    // Java Persistence configuration


}
