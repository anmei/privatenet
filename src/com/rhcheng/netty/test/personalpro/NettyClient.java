package com.rhcheng.netty.test.personalpro;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.rhcheng.netty.NettyCommon;
import com.rhcheng.netty.test.personalpro.codec.NettyMessageDecoder;
import com.rhcheng.netty.test.personalpro.codec.NettyMessageEncoder;
import com.rhcheng.netty.test.personalpro.handler.HeartBeatReqHandler;
import com.rhcheng.netty.test.personalpro.handler.LoginAuthReqHandler;

public class NettyClient {
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	
	public void connect(String host,int port){
		try {
			EventLoopGroup group = new NioEventLoopGroup();
			Bootstrap b = new Bootstrap();
			
			b.group(group).channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new NettyMessageDecoder(1024*1024,4,4,-8,0));
					ch.pipeline().addLast(new NettyMessageEncoder());
					ch.pipeline().addLast(new ReadTimeoutHandler(50));
					ch.pipeline().addLast(new LoginAuthReqHandler());
					ch.pipeline().addLast(new HeartBeatReqHandler());
					
				}
				
			});
		
			ChannelFuture f = b.connect(host, port).sync();
			System.out.println("client connect success……");
			f.channel().closeFuture().sync();
			System.out.println("client closed success.");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}finally{
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(5);// 客户端关闭连接后5秒进行重连
						connect(NettyCommon.host, NettyCommon.port);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
			
		}
		
	}
	
	public static void main(String[] args){
		new NettyClient().connect(NettyCommon.host, NettyCommon.port);
	}
	
	
}
