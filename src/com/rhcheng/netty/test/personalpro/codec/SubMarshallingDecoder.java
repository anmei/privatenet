package com.rhcheng.netty.test.personalpro.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

public class SubMarshallingDecoder extends MarshallingDecoder{

	public SubMarshallingDecoder(UnmarshallerProvider provider) {
		super(provider);
	}
	
	@Override
	public Object decode(ChannelHandlerContext ctx, ByteBuf in)
			throws Exception {
		return super.decode(ctx, in);
	}
	
}
