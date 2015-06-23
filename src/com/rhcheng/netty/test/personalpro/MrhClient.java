package com.rhcheng.netty.test.personalpro;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rhcheng.netty.NettyCommon;
import com.rhcheng.netty.test.personalpro.codec.MarshallingCodeFactory;
import com.rhcheng.netty.test.personalpro.handler.MrhClientHandler;

public class MrhClient {
	Logger log = LoggerFactory.getLogger(MrhClient.class);
	
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	private EventLoopGroup group = new NioEventLoopGroup();
	private Bootstrap b = new Bootstrap();
	
	public void connect(String host,int port){
		try {
			b.group(group).channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
					ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
//					ch.pipeline().addLast(new ReadTimeoutHandler(50));
//					ch.pipeline().addLast(new LoginAuthReqHandler());
//					ch.pipeline().addLast(new HeartBeatReqHandler());
					ch.pipeline().addLast(new MrhClientHandler());
					
				}
				
			});
		
			ChannelFuture f = b.connect(host, port).sync();
			System.out.println("client connect success.");
			f.channel().closeFuture().sync();
			System.out.println("client close success.");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			
		}finally{
//			group.shutdownGracefully();
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(5);// 客户端关闭连接后5秒进行重连
						System.out.println("try reconnect.");
						connect(NettyCommon.host, NettyCommon.port);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
			
		}
		
	}
	
	public static void main(String[] args){
		new MrhClient().connect(NettyCommon.host, NettyCommon.port);
	}
}
