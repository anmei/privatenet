package com.rhcheng.netty.test.personalpro.codec;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

public final class MarshallingCodeFactory {
	private MarshallingCodeFactory(){}
	
	public static MarshallingDecoder buildMarshallingDecoder(){
		MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		MarshallingConfiguration config = new MarshallingConfiguration();
		UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, config);
		return new MarshallingDecoder(provider);
	}
	public static MarshallingEncoder buildMarshallingEncoder(){
		MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		MarshallingConfiguration config = new MarshallingConfiguration();
		MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, config);
		return new MarshallingEncoder(provider);
	}
	
	

}
