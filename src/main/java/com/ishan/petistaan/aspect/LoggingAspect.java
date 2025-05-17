package com.ishan.petistaan.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component // @Component is used to mark this class as a Spring bean => @Aspect does not have component included
public class LoggingAspect {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(LoggingAspect.class);

    @AfterThrowing(value = "execution(* com.ishan.petistaan.*.*.*(..))", throwing = "exception")
    public void logAfterThrowingAdvice(JoinPoint joinPoint, Exception exception) {
        LOGGER.info("Exception in method " + joinPoint.getSignature().getDeclaringTypeName() + " with message: " + exception.getMessage());
    }

    @Around(value = "execution(* com.ishan.petistaan.*.*.*.*(..))")
    public Object logAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.info("Entering the method: " + proceedingJoinPoint.getSignature() + " with arguments: " + Arrays.toString(proceedingJoinPoint.getArgs()));
        Object result = proceedingJoinPoint.proceed();
        LOGGER.info("Exiting the method: " + proceedingJoinPoint.getSignature() + " with result: " + result);
        return result; // this is important to return the result of the method execution
    }
}
