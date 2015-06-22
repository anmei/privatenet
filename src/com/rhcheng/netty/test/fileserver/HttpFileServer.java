package com.rhcheng.netty.test.fileserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {
	
	public void run(String host,int port){
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup)
		.channel(NioServerSocketChannel.class)
		.childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast("http-decoder",new HttpRequestDecoder());
				ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
				ch.pipeline().addLast("http-encoder",new HttpResponseEncoder());
				ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
				ch.pipeline().addLast("fileServerHandler",new FileServerHandler());
				
			}
		});
		
		try {
			ChannelFuture f = b.bind(host,port).sync();
			System.out.println("文件服务器启动成功……");
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
			
		}
		
	}
	
	public static void main(String[] args){
		new HttpFileServer().run("127.0.0.1",8080);
		
	}
	
	
}
