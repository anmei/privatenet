package com.rhcheng.aop;

/**
 * 通知增强类, xml实现aop
 * 测试的切面，其实不是真正的切面而只是一个通知，需要将其与切点配置组装后才是真正的切面
 * @author RhCheng
 * 2015-5-10
 */
public class AopAspect {
    //前置通知  
    public void beforeAdvice() {  
    	System.out.println("===========before advice");  
    }  
    //后置最终通知  
    public void afterFinallyAdvice() {  
        System.out.println("===========after finally advice");  
    }  
    
}
