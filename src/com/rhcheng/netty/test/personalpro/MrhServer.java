package com.rhcheng.netty.test.personalpro;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import com.rhcheng.netty.NettyCommon;
import com.rhcheng.netty.test.personalpro.codec.MarshallingCodeFactory;
import com.rhcheng.netty.test.personalpro.handler.MrhServerHandler;
import com.rhcheng.netty.test.personalpro.pool.ChannelPool;

public class MrhServer {
	Logger log = LoggerFactory.getLogger(MrhServer.class);
	ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
	
	public void run(int port){
		EventLoopGroup bossgroup = new NioEventLoopGroup();
		EventLoopGroup workergroup = new NioEventLoopGroup();
		ServerBootstrap b = new ServerBootstrap();
		try {
			b.group(bossgroup,workergroup).channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_BACKLOG, 100)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingDecoder());
					ch.pipeline().addLast(MarshallingCodeFactory.buildMarshallingEncoder());
//					ch.pipeline().addLast(new ReadTimeoutHandler(50));
//					ch.pipeline().addLast(new LoginAuthRespHandler());
//					ch.pipeline().addLast(new HeartBeatRespHandler());
					ch.pipeline().addLast(new MrhServerHandler());
				}
				
			});
		
			ChannelFuture f = b.bind(port).sync();
			System.out.println("server bind success.");
			
			
			ses.scheduleWithFixedDelay(new Runnable() {
				@Override
				public void run() {
					System.out.println("pool size: "+ChannelPool.getChannelPool().size());
					for(Map.Entry<String, Channel> c:ChannelPool.getChannelPool().entrySet()){
						c.getValue().writeAndFlush(NettyCommon.buildNettyMsg());
						try {
							TimeUnit.SECONDS.sleep(5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}, 5, 5, TimeUnit.SECONDS);
			
			
			
			
			f.channel().closeFuture().sync();// 获取closeFuture，这样当close时可以得到通知
			System.out.println("server close success.");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
//			bossgroup.shutdownGracefully();
//			workergroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args){
		new MrhServer().run(NettyCommon.port);
	}
}
