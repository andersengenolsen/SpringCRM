package springcrm.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Logging aspect
 * Logging when methods are executed in the controller, dao and service package.
 */
@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* springcrm.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* springcrm.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("execution(* springcrm.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        logger.info(joinPoint.getSignature().toShortString());

        Object[] args = joinPoint.getArgs();

        for (Object o : args)
            logger.info(o.toString());
    }
}
