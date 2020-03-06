package com.ibaseit.ecommerceapplicationspring.config;
 
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 
@Configuration
@EnableJpaRepositories(basePackages = "com.ibaseit.ecommerceapplicationspring")
public class JpaConfig {
 
}
