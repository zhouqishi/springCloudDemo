package com.stone.user.aspect;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author yuanxiu
 * @date 2020/10/31
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around(value = "execution(* com.stone.user.service..*.*(..))")
    public void around(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        log.info("before execution 目标对象={}, 调用方法={},  调用入数={}", point.getSignature().getName(), point.getTarget(), JSONObject.toJSONString(args));
        Object object = point.proceed();
        log.info("after execution 目标对象={}, 调用方法={}, 返回结果={}", point.getSignature().getName(), point.getTarget(), JSONObject.toJSONString(object));
    }

}
