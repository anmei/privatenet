package com.rhcheng.util.fileuploadinfram;

import org.apache.commons.io.FilenameUtils;

/**
 * 文件上传文件夹或库
 * @author Administrator
 *
 */
public class UploadFileRepo {
	
	
	public final static int UNLIMIT = -1;

	/**
	 * 允许上传的文件类型
	 * null表示可以上传任意类型
	 */
	private String allowTypes = null; //image/jpeg 等

	/**
	 * 允许上传的文件大小默认无限
	 */
	private long allowFileSize = UNLIMIT; // 单位Byte

	/**
	 * 文件上传到服务器的相对路径 <br/>
	 * <p>
	 * 默认使用文件系统/考虑支持[文件服务器]或[分布式如tfs/fastdfs/hadoop]
	 * </p> 
	 */
	private String relativeUploadPath = null;
	
	/**
	 * 文件上传的绝对路径，当相对路径为null时才会选择绝对路径上传
	 * 当两个上传路径都为空时，上传失败
	 */
	private String absoluteUploadPath = null;
	
	
	private String checkFileSizeMessage = "文件大小不合适!";

	private String checkContentTypeMessage = "文件类型不合适!";
	

	public String getAbsoluteUploadPath() {
		return absoluteUploadPath;
	}

	public void setAbsoluteUploadPath(String absoluteUploadPath) {
		this.absoluteUploadPath = FilenameUtils.normalize(absoluteUploadPath);
	}

	public String getCheckContentTypeMessage() {
		return checkContentTypeMessage;
	}

	public void setCheckContentTypeMessage(String checkContentTypeMessage) {
		this.checkContentTypeMessage = checkContentTypeMessage;
	}

	public String getCheckFileSizeMessage() {
		return checkFileSizeMessage;
	}

	public void setCheckFileSizeMessage(String checkFileSizeMessage) {
		this.checkFileSizeMessage = checkFileSizeMessage;
	}

	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	public long getAllowFileSize() {
		return allowFileSize;
	}

	public void setAllowFileSize(long allowFileSize) {
		this.allowFileSize = allowFileSize;
	}

	public String getRelativeUploadPath() {
		return relativeUploadPath;
	}

	public void setRelativeUploadPath(String relativeUploadPath) {
		// 借助commons io 使路径自动匹配win/unix
		this.relativeUploadPath = FilenameUtils.normalize(relativeUploadPath);
	}


}
