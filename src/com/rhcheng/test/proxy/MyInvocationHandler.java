package com.rhcheng.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 这就是proxy的魅力
 * @author anmei
 *
 */
public class MyInvocationHandler implements InvocationHandler{
	SomeMethod impl = new SomeMethodImpl();
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("call invocation..");
		return method.invoke(impl, args);
	}

}
