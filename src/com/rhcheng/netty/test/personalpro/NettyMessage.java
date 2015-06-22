package com.rhcheng.netty.test.personalpro;

public class NettyMessage {
	private MessageHead head;
	private Object body;
	
	public MessageHead getHead() {
		return head;
	}
	public void setHead(MessageHead head) {
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
