package com.rhcheng.netty.test.personalpro.entity;

import java.io.Serializable;

public class NettyMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	private NettyMessageHead head;
	private Object body;
	
	public NettyMessageHead getHead() {
		return head;
	}
	public void setHead(NettyMessageHead head) {
		this.head = head;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "NettyMessage [head=" + head + ", body=" + body + "]";
	}
	
	
}
