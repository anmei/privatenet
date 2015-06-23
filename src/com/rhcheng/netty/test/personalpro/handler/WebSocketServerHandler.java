package com.rhcheng.netty.test.personalpro.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object>{
	private WebSocketServerHandshaker handShaker;
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if(msg instanceof FullHttpRequest){
			handleHttpReq(ctx,(FullHttpRequest)msg);
		}else if(msg instanceof WebSocketFrame){
			handleWebSocketFrame(ctx,(WebSocketFrame)msg);
		}
		
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	private void handleWebSocketFrame(ChannelHandlerContext ctx,
			WebSocketFrame msg) {
		if(msg instanceof CloseWebSocketFrame){
			handShaker.close(ctx.channel(), (CloseWebSocketFrame)msg.retain());
			return;
		}
		if(msg instanceof PingWebSocketFrame){
			ctx.channel().write(new PongWebSocketFrame(msg.content().retain()));
			return;
			
		}
		if(!(msg instanceof TextWebSocketFrame)){
			throw new UnsupportedOperationException(String.format("%s fram type not supported.", msg.getClass().getName()));
		}
		String reqmsg = ((TextWebSocketFrame)msg).text();
		ctx.write(new TextWebSocketFrame("欢迎，receive your message: "+reqmsg));
		
		
	}

	private void handleHttpReq(ChannelHandlerContext ctx, FullHttpRequest msg) {
		if(!msg.getDecoderResult().isSuccess() || !(msg.headers().get("Upgrade")).equals("websocket")){
			sendHttpResp(ctx,msg,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket", null, false);
		handShaker = wsFactory.newHandshaker(msg);
		if(handShaker == null){
			WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
		}else{
			handShaker.handshake(ctx.channel(), msg);
		}
		
	}
	private void sendHttpResp(ChannelHandlerContext ctx, FullHttpRequest msg,
			DefaultFullHttpResponse defaultFullHttpResponse) {
		ByteBuf buf = Unpooled.copiedBuffer(msg.getDecoderResult().toString(),CharsetUtil.UTF_8);
		defaultFullHttpResponse.content().writeBytes(buf);
		buf.release();
		ChannelFuture f = ctx.channel().writeAndFlush(defaultFullHttpResponse);
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		ctx.close();
		
	}

}
