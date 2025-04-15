package com.ishan.spring_boot_application;

import com.ishan.spring_boot_application.configuration.ApplicationConfiguration;
import com.ishan.spring_boot_application.service.OwnerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application {

	public static final int SAMPLE_OWNER_ID = 1;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		// Fetching the OwnerService bean from the application context
		OwnerService ownerService = applicationContext.getBean(OwnerService.class);
		String result = ownerService.findOwner(SAMPLE_OWNER_ID);
		System.out.println(result);
	}

}
