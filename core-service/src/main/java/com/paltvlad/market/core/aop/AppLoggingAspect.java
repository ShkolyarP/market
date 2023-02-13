package com.paltvlad.market.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AppLoggingAspect {
    @Pointcut("@annotation(com.paltvlad.market.core.aop.MeasureExecutionTime)")
    public void targetPointcut() {

    }


    @Around("targetPointcut()")
    public Object measureExecutionTime(ProceedingJoinPoint pjp) throws Throwable {

        Class<?> beenClass = pjp.getTarget().getClass();
        String methodName = pjp.getSignature().getName();

        long begin = System.currentTimeMillis();
        Object out = pjp.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;

        log.info("Время выполнения {}#{} : {} ms", beenClass.getName(), methodName, duration);

        return out;
    }
}
