package com.rhcheng.netty.test.personalpro.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;

import java.util.List;
import java.util.Map;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import com.rhcheng.netty.test.personalpro.entity.NettyMessage;
/**
 * 消息编码器
 * @author RhCheng
 * 2015年6月22日
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage>{

	SubMarshallingEncoder marshallingEncoder;
	
	public NettyMessageEncoder() {
		MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		MarshallingConfiguration config = new MarshallingConfiguration();
		MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, config);
		this.marshallingEncoder = new SubMarshallingEncoder(provider);
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, NettyMessage msg,
			List<Object> out) throws Exception {
		if(msg == null || msg.getHead() == null){
			throw new Exception("the message is null!");
		}
		
		/*
		 * 编码消息头
		 */
		ByteBuf sendBuf = Unpooled.buffer();
		sendBuf.writeInt(msg.getHead().getCrcCode());
		sendBuf.writeInt(msg.getHead().getLength());
		sendBuf.writeLong(msg.getHead().getSessionId());
		sendBuf.writeByte(msg.getHead().getType());
		sendBuf.writeByte(msg.getHead().getPriority());
		sendBuf.writeInt(msg.getHead().getAttachMent().size());
		
		/*
		 * 处理扩展的消息头信息
		 */
		String key;
		byte[] keyarray;
		Object value;
		for(Map.Entry<String,Object> param : msg.getHead().getAttachMent().entrySet()){
			key = param.getKey();
			keyarray = key.getBytes("UTF-8");
			sendBuf.writeInt(keyarray.length);
			sendBuf.writeBytes(keyarray);
			value = param.getValue();
			marshallingEncoder.encode(ctx, value, sendBuf);
			
		}
		
		/*
		 * 编码消息体
		 */
		if(msg.getBody() != null){
			marshallingEncoder.encode(ctx, msg.getBody(), sendBuf);
		}else{
			sendBuf.writeInt(0);// 对消息体赋值
		}
		sendBuf.setInt(4, sendBuf.readableBytes());// 消息总长度
		
		
	}
	
	
	
}
