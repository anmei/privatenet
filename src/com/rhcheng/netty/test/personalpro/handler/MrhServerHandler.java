package com.rhcheng.netty.test.personalpro.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rhcheng.netty.NettyCommon;
import com.rhcheng.netty.test.personalpro.entity.NettyMessage;
import com.rhcheng.netty.test.personalpro.entity.OrderResponse;
import com.rhcheng.netty.test.personalpro.pool.ChannelPool;

public class MrhServerHandler extends ChannelHandlerAdapter{
	private Logger log = LoggerFactory.getLogger(MrhServerHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String id = NettyCommon.getChannelId(ctx);
		ChannelPool.addChannel(id,ctx.channel());
		System.out.println("pool size: "+ChannelPool.getChannelPool().size());
		System.out.println("--->client["+id+"] had login in or had onnected.");
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		String id = NettyCommon.getChannelId(ctx);
		ChannelPool.delChannelByid(id);
		System.out.println("--->client["+id+"] had login out or had closed");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String id = NettyCommon.getChannelId(ctx);
		NettyMessage o = (NettyMessage)msg;
		System.out.println("server reveive a message:"+(String)o.getBody());
//		Channel toChannel = ChannelPool.getChannelByid(id);
//		toChannel.writeAndFlush(msg);
//		ctx.writeAndFlush(buildOrderRespEntity());
	}
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		String id = NettyCommon.getChannelId(ctx);
		System.out.println("server had catched a exception,maybe client["+id+"] had closed.");
		ctx.close();
	}
	
	
	OrderResponse buildOrderRespEntity(){
		OrderResponse or = new OrderResponse();
		or.setResCode(403);
		or.setResString("获取成功");
		return or;
	}
}
