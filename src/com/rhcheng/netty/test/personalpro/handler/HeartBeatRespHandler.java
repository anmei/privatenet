package com.rhcheng.netty.test.personalpro.handler;

import com.rhcheng.netty.test.personalpro.entity.NettyMessageHead;
import com.rhcheng.netty.test.personalpro.entity.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 服务端心跳机制
 * @author RhCheng
 * 2015年6月22日
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		NettyMessage message = (NettyMessage)msg;
		if(message.getHead()!=null && message.getHead().getType() == (byte)5){
			System.out.println("server has receive a heartbeat------->"+message);
			NettyMessage heartbeatresp = buildHeartResq();
			System.out.println("server has response a heartbeat------->"+message);
			ctx.writeAndFlush(heartbeatresp);
			
		}else{
			ctx.fireChannelRead(msg);
		}
		
	}
	
	NettyMessage buildHeartResq(){
		NettyMessage nm = new NettyMessage();
		NettyMessageHead head = new NettyMessageHead();
		head.setType((byte)6);
		nm.setHead(head);
		return nm;
	}
	
}
