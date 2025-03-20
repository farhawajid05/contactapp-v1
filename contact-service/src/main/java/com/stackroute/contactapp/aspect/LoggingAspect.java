package com.stackroute.contactapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.stackroute.contactapp.service.ContactService.*(..))")
    public void serviceMethods(){}

    @Before("serviceMethods()")
    public void logBeforeServiceMethod(JoinPoint joinPoint){
        LOGGER.info("******method started********");
        LOGGER.debug("Method Name : {}", joinPoint.getSignature().getName());
        LOGGER.debug("Method parameters : {}", Arrays.toString(joinPoint.getArgs()));
        LOGGER.info("******method ended********");

    }

    @AfterThrowing(value = "serviceMethods()", throwing = "exception")
    public void logAfterThrowingServiceMethod(JoinPoint joinPoint, Throwable exception){
        LOGGER.info("******After Throwing********");
        LOGGER.debug("Method Name : {}", joinPoint.getSignature().getName());
        LOGGER.debug("Method parameters : {}", Arrays.toString(joinPoint.getArgs()));
        LOGGER.debug("Exception occured : {}", exception.getStackTrace());
        LOGGER.info("******method ended********");
    }

/*    @Around(value = "serviceMethods()")
    public Object logAfterThrowingServiceMethod(ProceedingJoinPoint joinPoint){
        Object obj = null;
        try {
            //Action before the method execution

           obj =  joinPoint.proceed();
            //Action after the method execution
        } catch (Throwable throwable) {
            //Action after exception
            throwable.printStackTrace();
        }
    return obj;
    }*/

}
