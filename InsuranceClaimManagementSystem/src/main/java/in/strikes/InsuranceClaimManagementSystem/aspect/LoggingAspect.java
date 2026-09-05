package in.strikes.InsuranceClaimManagementSystem.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* in.strikes.InsuranceClaimManagementSystem.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        System.out.println("Started: "
                + joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println("Completed: "
                + joinPoint.getSignature().getName()
                + " | Time: "
                + (end - start)
                + " ms");

        return result;
    }
}