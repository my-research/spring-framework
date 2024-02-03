package com.github.dhslrl321.spring.aop;

import com.github.dhslrl321.logging.FailureLog;
import com.github.dhslrl321.logging.FailureLogRepository;
import com.github.dhslrl321.logging.FailureLoggingService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class FailureLoggingAspect {

    private final FailureLoggingService service;

    @Around("@annotation(props)")
    public Object doLogging(ProceedingJoinPoint joinPoint, FailureLogging props) throws Throwable {
        log.info("[voc-logging] logging aop starts {} {}", joinPoint.getSignature().toString(), props);
        log.info("[voc-logging] logging aop starts {} {}", joinPoint.getSignature().getName(), props);
        log.info("[voc-logging] logging aop starts {} {}", joinPoint.getSignature().getDeclaringType(), props);
        log.info("[voc-logging] logging aop starts {} {}", joinPoint.getSignature().getDeclaringTypeName(), props);

        try {
            return joinPoint.proceed();
        } catch (Exception e) {
            service.log(joinPoint.getArgs(), props, e);
            throw new RuntimeException(e);
        }
    }
}
