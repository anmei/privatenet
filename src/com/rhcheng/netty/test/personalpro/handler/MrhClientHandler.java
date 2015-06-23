package com.rhcheng.netty.test.personalpro.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rhcheng.netty.NettyCommon;
import com.rhcheng.netty.test.personalpro.entity.NettyMessage;
import com.rhcheng.netty.test.personalpro.entity.Order;

public class MrhClientHandler extends ChannelHandlerAdapter{
	Logger log = LoggerFactory.getLogger(MrhClientHandler.class);
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(NettyCommon.buildNettyMsg());
		
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		NettyMessage o = (NettyMessage)msg;
		System.out.println("client reveive a message:"+(String)o.getBody());
	}
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		System.out.println("client had catched a exception.");
		ctx.close();
	}
	
	
	Order buildOrderEntity(){
		Order o = new Order();
		o.setOid(1);
		o.setName("书籍名称");
		o.setPrice(45.3f);
		o.setDate(new Date());
		return o;
		
	}
	
}
