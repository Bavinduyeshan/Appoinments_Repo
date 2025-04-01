package com.Apooinments.Appoinments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages="com.Apooinments.Appoinments.service")
@SpringBootApplication
public class AppoinmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppoinmentsApplication.class, args);
	}

}
