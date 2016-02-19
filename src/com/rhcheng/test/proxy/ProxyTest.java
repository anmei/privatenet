package com.rhcheng.test.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {
	public static void main(String[] args) {
		SomeMethod ins = (SomeMethod)Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), 
				new Class[]{SomeMethod.class}, new MyInvocationHandler());
		System.out.println(ins.a());
	}
}
