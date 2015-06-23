package com.rhcheng.netty.test.personalpro.pool;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

public class ChannelPool {
	private static ConcurrentHashMap<String,Channel> channels = new ConcurrentHashMap<String,Channel>();
	
	public static Channel getChannelByid(String id){
		return channels.get(id);
	}
	public static void addChannel(String id,Channel c){
		channels.putIfAbsent(id, c);
	}
	public static Channel delChannelByid(String id){
		return channels.remove(id);
	}
	public static ConcurrentHashMap<String,Channel> getChannelPool(){
		return channels;
	}
	
	
}
