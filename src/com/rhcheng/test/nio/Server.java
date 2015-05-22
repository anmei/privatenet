package com.rhcheng.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

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
