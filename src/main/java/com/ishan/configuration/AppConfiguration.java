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

}
