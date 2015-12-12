package com.rhcheng.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.rhcheng.interceptor.CheckToken;

/**
 * 增强通知类, 注解实现aop
 * @author mei
 *
 */
@Aspect
public class NeedMetricInterceptor {
    
    
//    @Pointcut(value="@annotation(com.rhcheng.interceptor.CheckToken) && args(param)", argNames="param")
//    public void aspectjMethod(){}
    
    
//    @Around(value = "@annotation(com.beibei.common.metric.annotation.NeedMetric)&&@annotation(param)", argNames = "param")
//    @Around("within(com.beibei.demo.service.MemberServiceImpl.*) && args(param)")
    @Around(value="@annotation(com.rhcheng.interceptor.CheckToken)")
//    @Around(value="within(com.rhcheng.user.service.serviceimpl.*)")
    public Object process(ProceedingJoinPoint pjp) throws Throwable {
        boolean failure = false;
        long time = -1;
        try {
            long start = System.currentTimeMillis();
            Object result = pjp.proceed();
            time = System.currentTimeMillis() - start;
            return result;
        } catch (Throwable t) {
            failure = true;
            throw t;
        } finally {
            fireMetric(time, failure, null);
        }
    }

    private void fireMetric(long time, boolean failure, CheckToken param) {
        try {
            System.out.println("record logs……");
//            MetricType type = param.metricType();
//            String operation = param.operation();
//            if (operation == null || type == null || operation.isEmpty()) {
//                return;
//            }
//            if (failure) {
//                metricFactory.newMetric(type, operation).addCounter("call", 1).addCounter("failure", 1).emit();
//            } else {
//                metricFactory.newMetric(type, operation).addCounter("call", 1).addTime("processTime", time).emit();
//            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
}
