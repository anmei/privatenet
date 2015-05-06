package com.rhcheng.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component("testAnotationDI")
public class TestAnotationDI {
//	@Autowired
//	@Qualifier(value="b")
	private String a;
	
	@Autowired
	private ISomeClass isc;
	
	@Autowired
	public ApplicationContext apc;
	
	
//	@Autowired
//	public TestAnotationDI(String a, ISomeClass isc) {
//		super();
//		this.a = a;
//		this.isc = isc;
//	}
	
	
	public void print(){
		System.out.println(a);
		isc.methodOne();
	}

	
}
