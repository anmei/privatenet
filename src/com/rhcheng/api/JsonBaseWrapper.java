package com.rhcheng.api;

import java.util.HashMap;
import java.util.Map;

/**
 * json 基础数据包装器
 * 
 *
 */
public class JsonBaseWrapper {
	
	
	/**
	 * 调用是否成功
	 */
	private boolean success = true;
	
	/**
	 * 调用返回消息，如为成功可为空
	 */
	private String message = "";
	
	/**
	 * 版本
	 */
	private String version = "1.0";
	
	/**
	 * 调用结果代码
	 */
	private String resCode;
	

	/**
	 * 调用返回数据
	 */
	private Map<String, Object> data;


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		if(this.message == null) {
			if(this.success){
				this.message = "success";
			}else{
				this.message = "fail";
			}
		}
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}

	public String getResCode() {
		return resCode;
	}


	public void setResCode(String resCode) {
		this.resCode = resCode;
	}


	public Map<String, Object> getData() {
		if(null == data) {
			data = new HashMap<String, Object>();
		}
		return data;
	}
	

	@SuppressWarnings("unchecked")
	public void setData(Map<String, ? extends Object> data) {
		this.data = (Map<String, Object>) data;
	}


	@Override
	public String toString() {
		return "JsonBaseWrapper [success=" + success + ", message=" + message
				+ ", version=" + version + ", data=" + data + "]";
	}
	
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", this.isSuccess());
		map.put("message", this.getMessage());
		map.put("version", this.getVersion());
		map.put("data", this.data);
		return map;
	}
	
}
