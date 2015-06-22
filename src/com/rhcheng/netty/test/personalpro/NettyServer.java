package com.rhcheng.netty.test.personalpro;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class NettyServer {
	public void run(int port){
		try {
			EventLoopGroup bossgroup = new NioEventLoopGroup();
			EventLoopGroup workergroup = new NioEventLoopGroup();
			ServerBootstrap b = new ServerBootstrap();
			
			b.group(bossgroup,workergroup).channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 100)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new NettyMessageDecoder(1024*1024,4,4,-8,0));
					ch.pipeline().addLast(new NettyMessageEncoder());
					ch.pipeline().addLast(new ReadTimeoutHandler(50));
					ch.pipeline().addLast(new LoginAuthRespHandler());
					ch.pipeline().addLast(new HeartBeatRespHandler());
					
				}
				
			});
		
			ChannelFuture f = b.bind(port).sync();
			System.out.println("server bind success……");
			f.channel().closeFuture().sync();// 获取closeFuture，这样当close时可以得到通知
			System.out.println("server has closed.");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new NettyServer().run(NettyConstant.port);
	}
}
