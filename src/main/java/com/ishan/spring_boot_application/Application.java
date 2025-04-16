package com.ishan.spring_boot_application;

import com.ishan.spring_boot_application.repository.OwnerRepository;
import com.ishan.spring_boot_application.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(scanBasePackages = "com.ishan.spring_boot_application")
// @SpringBootApplication is a combination of @Configuration, @EnableAutoConfiguration, and @ComponentScan
public class Application implements CommandLineRunner {

	public static final int SAMPLE_OWNER_ID = 1;

	// now third party beans can be configured here itself using the @Bean annotation

	// @Autowired annotation is used to inject the OwnerService bean into this class
	// @SpringBootApplication also creates a bean of the Application class so things
	// can be autowired here
	@Autowired
	OwnerService ownerService1;
	@Autowired
	OwnerService ownerService2;
	@Autowired
	OwnerRepository ownerRepository1;
	@Autowired
	OwnerRepository ownerRepository2;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// note that the CommandLineRunner interface is used to run the code after the Spring Boot application has started
	// this is useful for testing the application
	// the run method here is internally called by Spring Boot inside the SpringApplication.run method
	@Override
	public void run(String... args) throws Exception {
		String serviceHashCode1 = String.valueOf(ownerService1.hashCode());
		String serviceHashCode2 = String.valueOf(ownerService2.hashCode());
		String repositoryHashCode1 = String.valueOf(ownerRepository1.hashCode());
		String repositoryHashCode2 = String.valueOf(ownerRepository2.hashCode());

		if(serviceHashCode1.equalsIgnoreCase(serviceHashCode2)) {
			System.out.println("OwnerService is of scope singleton");
		} else {
			System.out.println("OwnerService is of scope prototype");
		}

		if(repositoryHashCode1.equalsIgnoreCase(repositoryHashCode2)) {
			System.out.println("OwnerRepository is of scope singleton");
		} else {
			System.out.println("OwnerRepository is of scope prototype");
		}
	}
}
