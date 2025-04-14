package com.ishan;

import com.ishan.configuration.AppConfiguration;
import com.ishan.service.OwnerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{

    private static final int SAMPLE_OWNER_ID = 1;
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        // Get the OwnerService bean from the application context
        OwnerService ownerService = applicationContext.getBean(OwnerService.class);
        String result = ownerService.findOwner(SAMPLE_OWNER_ID);
        System.out.println(result);
        ((AnnotationConfigApplicationContext)applicationContext).close();
    }
}
