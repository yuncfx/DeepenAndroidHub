package com.deepen.android.hub.performance.aop;


import com.deepen.android.hub.performance.utils.LogUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class PerformanceAop {

    @Around("call(* com.optimize.performance.PerformanceApp.**(..))")
    public void getTime(ProceedingJoinPoint joinPoint) {
        // 签名
        Signature signature = joinPoint.getSignature();
        String name = signature.toShortString();
        long time = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//        LogUtils.i(name + " cost " + (System.currentTimeMillis() - time));
    }

    @Around("execution(* android.app.Activity.setContentView(..))")
    public void getSetContentViewTime(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.toShortString();
        long time = System.currentTimeMillis();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        LogUtils.i(name + " cost " + (System.currentTimeMillis() - time));
    }



}
