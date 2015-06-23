package com.rhcheng.netty.test.personalpro.handler;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.rhcheng.netty.test.personalpro.entity.NettyMessageHead;
import com.rhcheng.netty.test.personalpro.entity.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
/**
 * 客户端心跳机制
 * @author RhCheng
 * 2015年6月22日
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter{
	private volatile ScheduledFuture<?> heartbeat;
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		NettyMessage message = (NettyMessage)msg;
		if(message.getHead()!=null && message.getHead().getType()==(byte)4){
			heartbeat=ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 
					0, 5000, TimeUnit.MILLISECONDS);
		}else if(message.getHead()!=null && message.getHead().getType()==(byte)6){
			System.out.println("client receive heart beat---->"+message);
		}else{
			ctx.fireChannelRead(msg);
		}
		
		
		
	}
	
	class HeartBeatTask implements Runnable{
		private ChannelHandlerContext ctx;
		public HeartBeatTask(ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}
		@Override
		public void run() {
			NettyMessage heartbeatmsg = buildHeartReq();
			System.out.println("client has send a heartbeat------->"+heartbeatmsg);
			ctx.writeAndFlush(heartbeatmsg);
		}
		
		NettyMessage buildHeartReq(){
			NettyMessage nm = new NettyMessage();
			NettyMessageHead head = new NettyMessageHead();
			head.setType((byte)5);
			nm.setHead(head);
			return nm;
		}
				
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		if(heartbeat!=null){
			heartbeat.cancel(true);
			heartbeat=null;
		}
		ctx.fireExceptionCaught(cause);
	}
	
}
