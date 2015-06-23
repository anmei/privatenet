package com.rhcheng.netty;

import java.net.InetSocketAddress;

import io.netty.channel.ChannelHandlerContext;

import com.rhcheng.netty.test.personalpro.entity.NettyMessage;
import com.rhcheng.netty.test.personalpro.entity.NettyMessageHead;

public abstract class NettyCommon {
	public static String host = "127.0.0.1";
	public static int port = 8080;
	
	public static NettyMessage buildNettyMsg(){
		NettyMessage nm= new NettyMessage();
		NettyMessageHead nh = new NettyMessageHead();
		nm.setHead(nh);
		nm.setBody("test message.");
		return nm;
	}
	
	public static String getChannelId(ChannelHandlerContext ctx){
		InetSocketAddress inetadd = (InetSocketAddress)ctx.channel().remoteAddress();
		String id = inetadd.getAddress()+":"+inetadd.getPort();
		return id;

	}
}
