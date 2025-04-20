package com.ishan.spring_boot_application.aspect;

import com.ishan.spring_boot_application.exception.OwnerNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component // @Component is used to mark this class as a Spring bean => @Aspect does not have component included
public class LoggingAspect {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoggingAspect.class);

//    @Before(value = "execution(* com.ishan.spring_boot_application.*.*.*(..))") // pointcut expression
//    public void logBeforeAdvice(JoinPoint joinPoint) {
//        LOGGER.info("Entering the method " + joinPoint.getSignature().getDeclaringTypeName() + " with arguments " + Arrays.toString(joinPoint.getArgs()));
//    }

//    @After("execution(* com.ishan.spring_boot_application.*.*.*(..))")
//    public void logAfter(JoinPoint joinPoint){
//        LOGGER.info("Exiting the method " + joinPoint.getSignature().getDeclaringTypeName());
//    }

//    @AfterReturning(value = "execution(* com.ishan.spring_boot_application.*.*.*(..))", returning = "result")
//    public void logAfterReturningAdvice(JoinPoint joinPoint, String result) {
//        LOGGER.info("Exiting the method " + joinPoint.getSignature().getDeclaringTypeName() + " with result: " + result);
//    }

    @AfterThrowing(value = "execution(* com.ishan.spring_boot_application.*.*.*(..))", throwing = "ownerNotFoundException")
    public void logAfterThrowingAdvice(JoinPoint joinPoint, OwnerNotFoundException ownerNotFoundException) {
        LOGGER.info("Exception in method " + joinPoint.getSignature().getDeclaringTypeName() + " with message " + ownerNotFoundException.getMessage());
    }

    @Around(value = "execution(* com.ishan.spring_boot_application.*.*.*(..))")
    public Object logAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.info("Entering the method: " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + " with arguments: " + Arrays.toString(proceedingJoinPoint.getArgs()));
        Object result = proceedingJoinPoint.proceed();
        LOGGER.info("Exiting the method: " + proceedingJoinPoint.getSignature().getDeclaringTypeName() + " with result: " + result);
        return result; // this is important to return the result of the method execution
    }
}
