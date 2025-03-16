package org.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Aspect
@Component
public class ControllerAspect {

  @Before("execution(* com.example.demo.controller.*.*(..))")
  public void logMethodName(JoinPoint joinPoint) {
    System.out.println("Calling method: " + joinPoint.getSignature().getName());
  }

  @Around("execution(* com.example.demo.controller.*.*(..))")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    Instant start = Instant.now();
    Object proceed = joinPoint.proceed();
    Instant end = Instant.now();
    System.out.println("Execution time for " + joinPoint.getSignature().getName() + ": " + (end.toEpochMilli() - start.toEpochMilli()) + "ms");
    return proceed;
  }
}
