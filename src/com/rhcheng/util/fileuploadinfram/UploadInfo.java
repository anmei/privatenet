package com.rhcheng.util.fileuploadinfram;

import java.io.InputStream;

/**
 * 上传的文件信息接口，适用于不用的MVC框架
 * @author Administrator
 *	
 * 文件后缀、文件名、文件相对路径、文件绝对路径
 *
 */
public interface UploadInfo {
	
	/*
	 * 文件大小
	 */
	public long getFileSize();
	
	/*
	 * 文件类型
	 */
	public String getFileContentType();
	/*
	 * 原始文件名
	 */
	public String getOriginalFileName();
	/*
	 * 上传文件的表单名
	 */
	public String getFormName();
	/*
	 * 是否为空文件
	 */
	public boolean isEmpty();
	
	public InputStream getInputStream();
	
	public String getWebRootPath();

}
