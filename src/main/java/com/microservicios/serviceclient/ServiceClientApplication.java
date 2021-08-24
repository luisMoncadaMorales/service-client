package com.microservicios.serviceclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
public class ServiceClientApplication{

	public static void main(String[] args) {
		SpringApplication.run(ServiceClientApplication.class, args);
	}

}
