package com.rhcheng.util.file;

import java.util.Date;



/**
 * 导入导出文件信息
 * @author RhCheng
 * @date   2014-12-15
 */
public class FileInfo {
	private Long taskid;
	private Long userid;
	private Integer state;
	private String webUploadFilePath; // web access path, relative to context path, do not include file name
	private String localUploadFilePath; // local full file path, do not include file name
	private String tempUploadFilePath; // temporary upload file path, do not include file name
	private String webAccessFilePath; // web access path, relative to context path, do not include file name
	private String realFileName;
	private String storeFileName;
	private String fileSuffix;
	private Date procTime;
	private String remark;
	private String resMes;
	
	
	public String getResMes() {
		return resMes;
	}
	public void setResMes(String resMes) {
		this.resMes = resMes;
	}
	public String getTempUploadFilePath() {
		return tempUploadFilePath;
	}
	public void setTempUploadFilePath(String tempUploadFilePath) {
		this.tempUploadFilePath = tempUploadFilePath;
	}
	public Long getTaskid() {
		return taskid;
	}
	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getWebUploadFilePath() {
		return webUploadFilePath;
	}
	public void setWebUploadFilePath(String webUploadFilePath) {
		this.webUploadFilePath = webUploadFilePath;
	}
	public String getLocalUploadFilePath() {
		return localUploadFilePath;
	}
	public void setLocalUploadFilePath(String localUploadFilePath) {
		this.localUploadFilePath = localUploadFilePath;
	}
	public String getWebAccessFilePath() {
		return webAccessFilePath;
	}
	public void setWebAccessFilePath(String webAccessFilePath) {
		this.webAccessFilePath = webAccessFilePath;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	public String getStoreFileName() {
		return storeFileName;
	}
	public void setStoreFileName(String storeFileName) {
		this.storeFileName = storeFileName;
	}
	public String getFileSuffix() {
		return fileSuffix;
	}
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
	public Date getProcTime() {
		return procTime;
	}
	public void setProcTime(Date procTime) {
		this.procTime = procTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
