package com.example.happypetsday.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Around("@annotation(com.example.happypetsday.aspect.annotation.LoggingPointCut)")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint){
        long startTime = System.currentTimeMillis(); // 메소드 실행 전 시간 측정
        Object obj = null;
        try {
            obj = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long endTime = System.currentTimeMillis(); // 메소드 실행 후 시간 측정
        // 클래스 이름과 메소드 이름, 실행 시간을 로깅
        log.info("************************************");
        log.info(proceedingJoinPoint.getSignature().getDeclaringTypeName() + "."
                + proceedingJoinPoint.getSignature().getName() + " executed in " + (endTime - startTime) + "ms");
        log.info("************************************");

        return obj;
    }


}
