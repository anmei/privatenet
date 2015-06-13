package com.rhcheng.test.thread.serv;

public class ServClassOne {
	private int a;
	private int b;
	
	public int getA() {
		return a;
	}
	
	/*
	 * 使用内置锁，默认情况：调用方法所在的对象即为锁
	 */
	public synchronized void setA(int a) {
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	
}
