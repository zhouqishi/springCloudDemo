package com.stone.user.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hystrix Dashboard
 *
 * 访问url http://127.0.0.1:9003/hystrix
 * http://localhost:9002/actuator/hystrix.stream
 */
@SpringBootApplication
@EnableHystrixDashboard
public class DashboardApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(DashboardApplication.class);
    }
}
