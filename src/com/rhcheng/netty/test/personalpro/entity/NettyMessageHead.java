package com.rhcheng.netty.test.personalpro.entity;

import java.io.Serializable;
import java.util.Map;

public class NettyMessageHead implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer crcCode;// netty消息协议标志+主版本号+次版本号
	private Integer length;// 消息总长度（包括head、body）
	private Long sessionId;// 会话id
	private String cliendid;//客户端标识
	private byte type; //消息类型：0业务请求 1业务应答 2业务one way消息(即使请求又是响应) 3握手请求 4握手应答 5心跳请求 6心跳应答
	private byte priority;// 消息优先级
	private Map<String,Object> attachMent;// 用于扩展消息头
	
	public String getCliendid() {
		return cliendid;
	}
	public void setCliendid(String cliendid) {
		this.cliendid = cliendid;
	}
	public Integer getCrcCode() {
		return crcCode;
	}
	public void setCrcCode(Integer crcCode) {
		this.crcCode = crcCode;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public Long getSessionId() {
		return sessionId;
	}
	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}
	public byte getPriority() {
		return priority;
	}
	public void setPriority(byte priority) {
		this.priority = priority;
	}
	public Map<String, Object> getAttachMent() {
		return attachMent;
	}
	public void setAttachMent(Map<String, Object> attachMent) {
		this.attachMent = attachMent;
	}
	@Override
	public String toString() {
		return "MessageHead [crcCode=" + crcCode + ", length=" + length
				+ ", sessionId=" + sessionId + ", type=" + type + ", priority="
				+ priority + ", attachMent=" + attachMent + "]";
	}
	
	
	
	
	
}	
