package com.ishan.spring_boot_application;

import com.ishan.spring_boot_application.service.OwnerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(scanBasePackages = "com.ishan.spring_boot_application")
// @SpringBootApplication is a combination of @Configuration, @EnableAutoConfiguration, and @ComponentScan
public class Application {

	// now third party beans can be configured here itself using the @Bean annotation
	public static final int SAMPLE_OWNER_ID = 1;
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		// Fetching the OwnerService bean from the application context
		OwnerService ownerService = applicationContext.getBean(OwnerService.class);
		String result = ownerService.findOwner(SAMPLE_OWNER_ID);
		((AnnotationConfigApplicationContext)applicationContext).close();
		System.out.println(result);
	}

}
