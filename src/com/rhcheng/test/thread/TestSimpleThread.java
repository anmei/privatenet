package com.rhcheng.test.thread;
/**
 * 基本的创建线程的两种方式
 * 不足：线程中的任务执行策略与任务的提交是耦合在一起的
 * @author RhCheng
 * @date   2015年5月27日
 */
public class TestSimpleThread {
	
	public static void main(String[] args) {
		
		// 方法一
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				int i=0;
//				while(true){
//					System.out.println(++i+":"+Thread.currentThread().getName());
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
		
		// 方法二
		new Thread1().start();
		
	}
	
	
	static class Thread1 extends Thread{
		@Override
		public void run() {
			int i=0;
			while(true){
				System.out.println(++i+":"+Thread.currentThread().getName());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
