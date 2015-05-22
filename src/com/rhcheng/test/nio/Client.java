package com.rhcheng.test.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Client {
	private SocketChannel sc;
	private Selector se;
	
	private Client initClient(String ip,int port) throws IOException{
		sc = SocketChannel.open();
		sc.connect(new InetSocketAddress(ip,port));
		sc.configureBlocking(false);
		se = Selector.open();
		sc.register(se, SelectionKey.OP_CONNECT);
		return this;
	}
	
	private void conn() throws IOException{
		int count = 9;
		while(true && count-- > 0){
			System.out.println("client ok");
			se.select();
			Iterator<SelectionKey> it = se.selectedKeys().iterator();
			System.out.println(it.hasNext());
			while(it.hasNext()){
//				SelectionKey selekey = it.next();
//				
//				System.out.println(selekey.isConnectable()+" "+selekey.isReadable()+" "+selekey.isWritable());
//				
//				it.remove();
//				if(selekey.isConnectable()){
//					SocketChannel c = (SocketChannel) selekey.channel();
//					if(c.isConnectionPending()){
//						c.finishConnect();
//					}
//					c.configureBlocking(false);
////					waitForInput(c);
//					c.register(se, SelectionKey.OP_READ);
//				}else if(selekey.isReadable()){
//					SocketChannel c1 = (SocketChannel) selekey.channel();
//					c1.configureBlocking(false);
//					read(c1);
//					waitForInput(c1);
//					c1.register(se, SelectionKey.OP_READ);
//				}
			}
			
		}
	}
	
	
	public static void send(String msg,SocketChannel soc) throws IOException{
		System.out.println("发送消息："+msg);
		soc.write(ByteBuffer.wrap(msg.getBytes()));
	}
	public static String read(SocketChannel soc) throws IOException{
		ByteBuffer bf = ByteBuffer.allocate(1024);
		soc.read(bf);
		System.out.println("收到消息："+soc.toString());
		return soc.toString();
		
	}
	public static void waitForInput(SocketChannel soc) throws IOException{
		System.out.println("等待输入消息:");
		BufferedReader br; 
		String l;
		br = new BufferedReader(new InputStreamReader(System.in));
		l = br.readLine();
		send(l,soc);
	}
	
	public static void main(String[] args) throws IOException {
		new Client().initClient("localhost",8280).conn();
		
	}
}
