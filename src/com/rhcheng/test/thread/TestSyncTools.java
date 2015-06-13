package com.rhcheng.test.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import com.rhcheng.test.thread.serv.ServClassOne;
/**
 * 测试众多同步、互斥工具方法\类
 * 
 * volatile\final\ThreadLoal
 * synchronize\ReentrantLock|Condition
 * 
 * {@code CountDownLatch}
 * {@code CyclicBarrier}
 * {@code Semaphore}
 * 
 * @author RhCheng
 * @date   2015年5月29日
 */
public class TestSyncTools {
	static ServClassOne sc = new ServClassOne();
	
	
	public static void main(String[] args){
		// 使用内置锁
//		int i=0;
//		while(i++ <= 4){
//			new Thread1(sc,i).start();
//			
//		}
		
		
		
	}
	
	
	
	
	static class Thread1 extends Thread{
		private ServClassOne s;
		private int c;
		
		public int getC() {
			return c;
		}
		public void setC(int c) {
			this.c = c;
		}
		public ServClassOne getS() {
			return s;
		}
		public void setS(ServClassOne s) {
			this.s = s;
		}

		public Thread1(ServClassOne s,int c) {
			super();
			this.s = s;
			this.c = c;
		}
		
		
		@Override
		public void run() {
			s.setA(c);
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
