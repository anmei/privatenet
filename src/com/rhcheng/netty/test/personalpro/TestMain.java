package com.rhcheng.netty.test.personalpro;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.rhcheng.netty.NettyCommon;
import com.rhcheng.netty.test.personalpro.pool.ChannelPool;

public class TestMain {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("pool size: "+ChannelPool.getChannelPool().size());
		for(Map.Entry<String, Channel> c:ChannelPool.getChannelPool().entrySet()){
			c.getValue().writeAndFlush(NettyCommon.buildNettyMsg());
			TimeUnit.SECONDS.sleep(5);
		}
		
	}
	
}
