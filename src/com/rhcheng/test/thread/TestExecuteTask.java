package com.rhcheng.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executor是对任务执行更好的抽象
 * 好处：将任务执行策略与任务的提交解耦开来
 * 
 * 线程池使用模型：阻塞队列+线程池
 * 阻塞队列：LinkedBlockingQueue（无界）\ArrayBlockingQueue（有界）\SynchrnousQueue（同步）
 * SynchrnousQueue(同步队列)：每个插入操作必须等待另一个线程的对应移除操作，除非另一个线程试图移除某个元素，否则也不能（使用任何方法）插入元素，同步队列没有任何内部容量，甚至连一个队列的容量都没有。
 * 强烈建议程序员使用较为方便的 Executors 工厂方法
 * 具体的参考ThreadPoolExecutor类的jdk文档
 * 
 * Runnable:大多数情况下，如果只想重写 run() 方法，而不重写其他 Thread 方法，那么应使用 Runnable 接口。这很重要，因为除非程序员打算修改或增强类的基本行为，否则不应为该类创建子类。
 * Callable：表示可以返回结果并且可能抛出异常的任务
 * Future：可以取消任务的执行，可以获取执行结果等
 * 
 * 
 * 交通灯信号管理
 * 
 * @author RhCheng
 * @date   2015年5月27日
 */
public class TestExecuteTask {
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(2);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
