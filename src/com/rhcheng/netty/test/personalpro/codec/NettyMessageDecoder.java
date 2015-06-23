package com.rhcheng.netty.test.personalpro.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

import java.util.HashMap;
import java.util.Map;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import com.rhcheng.netty.test.personalpro.entity.NettyMessage;
import com.rhcheng.netty.test.personalpro.entity.NettyMessageHead;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder{
	
	SubMarshallingDecoder marshallingDecoder;
	
	public NettyMessageDecoder(int maxFrameLength,int lengthFieldOffset,int lengthFieldLength,
            int lengthAdjustment, int initialBytesToStrip) {
		super(maxFrameLength,lengthFieldOffset, lengthFieldLength,
	             lengthAdjustment, initialBytesToStrip);
		
		MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		MarshallingConfiguration config = new MarshallingConfiguration();
		UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, config);
		this.marshallingDecoder = new SubMarshallingDecoder(provider);
		
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in)
			throws Exception {
		ByteBuf frame = (ByteBuf)super.decode(ctx, in);
		if(frame == null){return null;}
		
		NettyMessage msg = new NettyMessage();
		NettyMessageHead head = new NettyMessageHead();
		head.setCrcCode(in.readInt());
		head.setLength(in.readInt());
		head.setSessionId(in.readLong());
		head.setType(in.readByte());
		head.setPriority(in.readByte());
		
		/*
		 * 扩展消息头解码
		 */
		int size = in.readInt();
		if(size > 0){
			Map<String,Object> attach = new HashMap<String,Object>(size);
			byte[] keyarray;
			String key;
			int keysize;
			for(int i=0;i<size;i++){
				keysize = in.readInt();
				keyarray = new byte[keysize];
				in.readBytes(keyarray);
				key = new String(keyarray,"Utf-8");
				attach.put(key, marshallingDecoder.decode(ctx, in));
				
			}
		}
		
		if(in.readInt()>0){
			msg.setBody(marshallingDecoder.decode(ctx, in));
		}
		msg.setHead(head);
		return msg;
		
	}
	

}
