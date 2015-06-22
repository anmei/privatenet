package com.rhcheng.netty.test.timesrv;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {
	byte[] reqmes;
	
	
	public TimeClientHandler() {
		reqmes = ("time order"+System.lineSeparator()).getBytes();
	}
	
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf fi;
		for(int i=0;i<100;i++){
			fi = Unpooled.copiedBuffer(reqmes);
			ctx.writeAndFlush(fi);
		}
	}
	

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
//		ByteBuf bf = (ByteBuf)msg;
//		byte[] respo = new byte[bf.readableBytes()];
//		bf.readBytes(respo);
//		String body = new String(respo,"UTF-8");
		
		String body = (String)msg;
		System.out.println("now time is: "+body);
		
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}
	

}
