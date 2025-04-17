package com.ishan.spring_boot_application;

import com.ishan.spring_boot_application.service.OwnerService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ishan.spring_boot_application")
// @SpringBootApplication is a combination of @Configuration, @EnableAutoConfiguration, and @ComponentScan
public class Application implements CommandLineRunner {

	public static final int SAMPLE_OWNER_ID_ODD = 1;
	public static final int SAMPLE_OWNER_ID_EVEN = 2;
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Application.class);
	// why use logger? because console messages through out.println() are not recommended in production code as they can't be read on server where console output is not available

	// now third party beans can be configured here itself using the @Bean annotation

	// @Autowired annotation is used to inject the OwnerService bean into this class
	// @SpringBootApplication also creates a bean of the Application class so things
	// can be autowired here
	@Autowired
	OwnerService ownerService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// note that the CommandLineRunner interface is used to run the code after the Spring Boot application has started
	// this is useful for testing the application
	// the run method here is internally called by Spring Boot inside the SpringApplication.run method
	@Override
	public void run(String... args) throws Exception {
		String result = ownerService.findOwner(SAMPLE_OWNER_ID_EVEN);
		LOGGER.info(result);
		try{
			// this will throw an exception
			result = ownerService.findOwner(SAMPLE_OWNER_ID_ODD);
			LOGGER.info(result);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
	}
}
