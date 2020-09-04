package com.stone.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * user
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class UserApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserApplication.class);
    }
}
