package com.ishan;

import com.ishan.configuration.AppConfiguration;
import com.ishan.service.OwnerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App 
{

    private static final int SAMPLE_OWNER_ID = 1;
    public static void main( String[] args )
    {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        // Get the OwnerService bean from the application context
        OwnerService ownerService = applicationContext.getBean("OwnerServiceImpl",OwnerService.class);
        String result = ownerService.findOwner(SAMPLE_OWNER_ID);
        System.out.println(result);
        OwnerService ownerServiceNoSql = applicationContext.getBean("OwnerServiceImplNoSql",OwnerService.class);
        String resultNoSql = ownerServiceNoSql.findOwner(SAMPLE_OWNER_ID);
        System.out.println(resultNoSql);
        ((AnnotationConfigApplicationContext)applicationContext).close();
    }
}
