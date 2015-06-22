package com.rhcheng.netty.test.timesrv;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
//		ByteBuf bf = (ByteBuf)msg;
//		byte[] req = new byte[bf.readableBytes()];
//		bf.readBytes(req);
//		String body = new String(req,"UTF-8");
		String body = (String)msg;
		System.out.println("server receive msg: "+body);
		String curtime = body.equals("time order")? new Date(System.currentTimeMillis()).toString():"bad request";
		curtime += System.lineSeparator();
		ByteBuf respon = Unpooled.copiedBuffer(curtime.getBytes());
		ctx.write(respon);
		
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
	}

}
