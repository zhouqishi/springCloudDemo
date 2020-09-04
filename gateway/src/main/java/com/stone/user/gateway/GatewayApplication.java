package com.stone.user.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * gateway
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class GatewayApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(GatewayApplication.class);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user_route",
                        r -> r.path("/USER-SERVICE/**").filters(f -> f.stripPrefix(1)).uri("lb://USER-SERVICE")
                        //.uri("http://localhost:9002")
                )
                .build();
    }
}
