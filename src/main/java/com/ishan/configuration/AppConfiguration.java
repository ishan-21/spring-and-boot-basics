package com.ishan.configuration;

import com.ishan.repository.OwnerRepository;
import com.ishan.repository.OwnerRepositoryImpl;
import com.ishan.service.OwnerService;
import com.ishan.service.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public OwnerRepository ownerRepository(){
        return new OwnerRepositoryImpl();
    }

    @Bean
    public OwnerService ownerService(@Autowired OwnerRepository ownerRepository){
        return new OwnerServiceImpl(ownerRepository);
    }
}
