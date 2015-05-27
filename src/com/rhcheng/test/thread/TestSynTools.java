package com.rhcheng.test.thread;

import com.rhcheng.test.thread.serv.ServClassOne;

public class TestSynTools {
	static ServClassOne sc = new ServClassOne();
	
	public static void main(String[] args){
		int i=0;
		while(i++ <= 4){
			new Thread1(sc,i).start();
			
		}
		
		
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
