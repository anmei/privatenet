package com.rhcheng.test;

import org.springframework.stereotype.Service;

@Service("someClass")
public class SomeClass implements ISomeClass{

	@Override
	public void methodOne() {
		System.out.println("methodOne");
		
	}

}
