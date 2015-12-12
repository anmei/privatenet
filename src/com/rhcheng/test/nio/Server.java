package com.rhcheng.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
/**
 * 基于jdk nio
 * 1、创建SocketChannel
 * 2、创建Selector
 * 3、注册channel与事件到Selector上
 * 4、非阻塞体现在建立连接、读写等IO操作上，由事件驱动
 * 
 * 网络通信框架reactor模式：单线程模式、多线程模式、主从多线程模式
 * 串行化设计理念：一个线程可以处理多个channel，但是一个channel只由一个线程处理
 * Netty的定时任务调度就是基于时间轮算法调度
 * 
 * 
 * 
 * @author RhCheng
 * @date   2015年6月29日
 */
public class Server {
	private ServerSocketChannel ssc;
	private Selector se;
	
	private Server initServer(int port) throws IOException{
		ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(port));
		ssc.configureBlocking(false);
		se = Selector.open();
		ssc.register(se, SelectionKey.OP_ACCEPT);
		return this;
		
	}
	
	private void accept() throws IOException{
		while(true){
			System.out.println("server ok");
			se.select();
			Iterator<SelectionKey> sek = se.selectedKeys().iterator();
			while(sek.hasNext()){
				SelectionKey sk = sek.next();
				sek.remove();
				if(sk.isAcceptable()){
					ServerSocketChannel ssca = (ServerSocketChannel)sk.channel();
					SocketChannel ssc1 = ssca.accept();
					ssc1.configureBlocking(false);
					ssc1.register(se, SelectionKey.OP_READ);
					
					Client.read(ssc1);
					Client.waitForInput(ssc1);
				}else if(sk.isReadable()){
					SocketChannel sc1 = (SocketChannel)sk.channel();
					sc1.configureBlocking(false);
					Client.read(sc1);
					sc1.register(se, SelectionKey.OP_READ);
					Client.waitForInput(sc1);
				}
				
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Server().initServer(8280).accept();
		
	}
	
}
