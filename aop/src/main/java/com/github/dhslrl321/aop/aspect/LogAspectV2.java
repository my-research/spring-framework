package com.github.dhslrl321.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * PointCut 과 Around 분리
 */
@Slf4j
@Aspect
public class LogAspectV2 {

    @Pointcut("execution(* com.github.dhslrl321.aop..*(..))") //pointcut expression
    private void allOrder(){} // <- pointcut signature

    @Around("allOrder()") // pointcut
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log] {}", joinPoint.getSignature()); //join point 시그니처
        return joinPoint.proceed();
    }
}
