package com.rhcheng.test;

public class TestThread {
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i=0;
				while(true){
					System.out.println(++i+":"+Thread.currentThread().getName());
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
	
	
}
