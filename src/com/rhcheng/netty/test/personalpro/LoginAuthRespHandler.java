package com.rhcheng.netty.test.personalpro;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
/**
 * 服务端处理登陆请求
 * @author RhCheng
 * 2015年6月22日
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter{
	
	private static Map<String,Boolean> haslogin = new ConcurrentHashMap<String,Boolean>();
	private String[] whiteip = new String[]{"127.0.0.1","192.168.1.110"}; 
	
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		NettyMessage message = (NettyMessage)msg;
		System.out.println("server receive:" +message);
		NettyMessage loginResq;
		String reqip = ctx.channel().remoteAddress().toString();
		System.out.println("client ip is :" +reqip);
		if(message.getHead()!=null && message.getHead().getType() == (byte)3){
			// 重复登陆
			if(haslogin.containsKey(reqip)){
				loginResq = buildLoginAuthResqMsg((byte)-1);
			}else{
				boolean ok = false;
				for(String wp:whiteip){
					if(wp.equalsIgnoreCase(reqip)){
						ok = true;
						break;
					}
				}
				loginResq = ok?buildLoginAuthResqMsg((byte)0):buildLoginAuthResqMsg((byte)-1);
				if(ok){haslogin.put(reqip, true);}
				System.out.println("login response is: body="+loginResq.getBody());
				ctx.writeAndFlush(loginResq);
			}
			
		}else{
			ctx.fireChannelRead(msg);
	
		}
		
		
	}
	
	/*
	 * 构建登陆请求应答消息
	 */
	NettyMessage buildLoginAuthResqMsg(byte result){
		NettyMessage msg = new NettyMessage();
		MessageHead head = new MessageHead();
		head.setType((byte)4);
		msg.setHead(head);
		msg.setBody(result);
		return msg;
		
	}
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		haslogin.remove(ctx.channel().remoteAddress().toString());
		ctx.close();
		ctx.fireExceptionCaught(cause);
	}
	
}
