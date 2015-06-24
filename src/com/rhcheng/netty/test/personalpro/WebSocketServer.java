package com.rhcheng.netty.test.personalpro;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.rhcheng.netty.NettyCommon;
import com.rhcheng.netty.test.personalpro.codec.MarshallingCodeFactory;
import com.rhcheng.netty.test.personalpro.handler.MrhServerHandler;
import com.rhcheng.netty.test.personalpro.handler.WebSocketServerHandler;
import com.rhcheng.netty.test.personalpro.pool.ChannelPool;

public class WebSocketServer {
	public void run(int port){
		EventLoopGroup bossgroup = new NioEventLoopGroup();
		EventLoopGroup workergroup = new NioEventLoopGroup();
		ServerBootstrap b = new ServerBootstrap();
		try {
			b.group(bossgroup,workergroup).channel(NioServerSocketChannel.class)
			.handler(new LoggingHandler(LogLevel.INFO))
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new HttpServerCodec());
					ch.pipeline().addLast(new HttpObjectAggregator(65536));
					ch.pipeline().addLast(new ChunkedWriteHandler());
					ch.pipeline().addLast(new WebSocketServerHandler());
				}
				
			});
		
			ChannelFuture f = b.bind(port).sync();
			System.out.println("websocket server bind success.");
			f.channel().closeFuture().sync();// 获取closeFuture，这样当close时可以得到通知
			System.out.println("server close success.");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			bossgroup.shutdownGracefully();
			workergroup.shutdownGracefully();
		}
	}
	
	public static void main(String[] args){
		new WebSocketServer().run(NettyCommon.port);
	}
}
