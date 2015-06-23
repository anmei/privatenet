package com.rhcheng.netty.test.personalpro.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

public class SubMarshallingEncoder extends MarshallingEncoder{

	public SubMarshallingEncoder(MarshallerProvider provider) {
		super(provider);
	}
	
	@Override
	public void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
			throws Exception {
		super.encode(ctx, msg, out);
	}
	
}

