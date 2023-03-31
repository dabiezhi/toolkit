package com.bloom.springboot.liteflow.component.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author curry
 * Created by on 2023-03-31 4:55 PM
 */
@Aspect
@Component
public class CustomAspect {

    @Pointcut("execution(* com.yomahub.liteflow.core.NodeIfComponent.processIf())")
    public void cut() {
    }

//    @Pointcut("execution(* com.bloom.springboot.liteflow.node.DCmp.process())")
//    public void cut() {
//    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        //do before business
        Object returnObj = jp.proceed();
        //do after business
        return returnObj;
    }
}