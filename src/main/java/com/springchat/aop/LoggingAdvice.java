package com.springchat.aop;

import com.springchat.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 *
 * @author 984351
 */
@Aspect
public class LoggingAdvice {

    private ILogger logger;

    public LoggingAdvice(ILogger logger) {
        this.logger = logger;
    }

    @After("execution(* com.springchat.dao.*.*(..))")
    public void log(JoinPoint joinpoint) {
        logger.log("Call was made to:" + joinpoint.getSignature().getName()
                + " on " + joinpoint.getTarget().getClass());
    }
}
