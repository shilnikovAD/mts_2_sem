package org.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Aspect
@Component
public class ControllerAspect {

  private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

  private int counter = 0;

  public int getCounter() {
    return counter;
  }

  // Before advice to log method execution start
  @Before("execution(* org.example.controller.*.*(..))")
  public void incrementCounterBefore(JoinPoint joinPoint) {
    logger.info("Before method: " + joinPoint.getSignature().getName());
    logger.info("Counter before increment: " + counter);
    counter += 2;
    logger.info("Counter after increment: " + counter);
  }




  @Around("execution(* com.example.controller.*.*(..))")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    Instant start = Instant.now();
    Object proceed = joinPoint.proceed();
    Instant end = Instant.now();
    logger.info("Execution time for " + joinPoint.getSignature().getName() + ": " + (end.toEpochMilli() - start.toEpochMilli()) + "ms");
    return proceed;
  }
}
