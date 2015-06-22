package com.rhcheng.netty.test.personalpro;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
/**
 * 客户端发起登陆请求
 * @author RhCheng
 * 2015年6月22日
 */
public class LoginAuthReqHandler extends ChannelHandlerAdapter{
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("send to server");
		ctx.writeAndFlush(buildLoginAuthReqMes());
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		NettyMessage message = (NettyMessage)msg;
		if(message.getHead()!=null && message.getHead().getType() == (byte)3){
			byte loginRes = (byte)message.getBody();
			if(loginRes != (byte)0){ // 登陆失败
				ctx.close();
			}else{
				System.out.println("login success!");
				ctx.fireChannelRead(msg);
			}
			
		}else{
			ctx.fireChannelRead(msg);
		}
		
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		
		ctx.fireExceptionCaught(cause);
	}
	
	/**
	 * 构建登陆认证消息
	 * @authod RhCheng
	 * @return
	 */
	NettyMessage buildLoginAuthReqMes(){
		NettyMessage msg = new NettyMessage();
		MessageHead head = new MessageHead();
		head.setType((byte)3);
		msg.setHead(head);
		return msg;
	}
	
	
}
