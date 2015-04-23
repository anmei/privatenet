package com.rhcheng.util.fileuploadinfram;

import org.apache.commons.io.FilenameUtils;

/**
 * 文件上传结果
 * @author Administrator
 *
 */
public class UploadResult {
	
	/**
	 *  上传成功标志
	 *  true 成功
	 *  false 失败
	 */
	private boolean success;
	
	/**
	 *  上传结果消息
	 */
	private String message;
	
	/**
	 *  上传的input表单名称
	 */
	private String uploadFormName;
	
	
	/**
	 *  文件上传时在表单中的名字,包含扩展名
	 *  即原始文件名
	 */
	private String realFileName;
	
	/*
	 * 服务器中保存的文件名
	 */
	private String fileNameInServer;
	
	/**
	 *  文件引用地址，即文件上传的相对路径+文件名,只有当文件上传到专门的文件服务器时才用
	 */
	private String webLinkfileName = null;
	
	/**
	 * 文件上传的绝对路径+文件名，只有当相对路径为空时才会定义绝对路径
	 */
	private String absoluteFileName = null;
	
	

	public String getAbsoluteFileName() {
		return absoluteFileName;
	}

	public void setAbsoluteFileName(String absoluteFileName) {
		this.absoluteFileName = absoluteFileName;
	}

	/**
	 * 文件扩展名 
	 */
	private String fileExtName;
	
	
	public String getRealFileName() {
		return realFileName;
	}

	public void setRealFileName(String realFileName) {
		this.fileExtName = FilenameUtils.getExtension(realFileName);
		this.realFileName = realFileName;
	}

	public String getFileNameInServer() {
		return fileNameInServer;
	}

	public void setFileNameInServer(String fileNameInServer) {
		this.fileNameInServer = fileNameInServer;
	}

	public String getMessage() {
		if(this.success){
			this.message = "上传成功";
		}
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getUploadFormName() {
		return uploadFormName;
	}

	public void setUploadFormName(String uploadFormName) {
		this.uploadFormName = uploadFormName;
	}

	public String getWebLinkfileName() {
		return webLinkfileName;
	}

	public void setWebLinkfileName(String webLinkfileName) {
		this.webLinkfileName = webLinkfileName;
	}

	public String getFileExtName() {
		
		return fileExtName;
	}

	

}
