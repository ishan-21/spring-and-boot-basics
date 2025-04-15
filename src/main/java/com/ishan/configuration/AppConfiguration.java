package com.ishan.configuration;

import com.ishan.repository.OwnerRepository;
import com.ishan.repository.OwnerRepositoryImpl;
import com.ishan.service.OwnerService;
import com.ishan.service.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackages = "com.ishan")
public class AppConfiguration {

    /*
    Important Note:

    > This class currently has almost no use as its only purpose is to
    list down the two annotations: @Configuration and @ComponentScan. These
    can be shifted to the main class (App.java) and the code will still work.

    > Why I am not deleting this class => because it is best practice to create @Bean(s)
    of third partu libraries and other classes that CANNOT annotated with @Component, for example
    ObjectMapper, DataSource, etc. So this class is a placeholder for future use.
     */
    
}
