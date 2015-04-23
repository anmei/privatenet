package com.rhcheng.news.entity;

import java.util.Date;
import java.util.List;

/**
 * 新闻概要
 * @author RhCheng
 * @date   2014-8-4
 */
public class NewsAbstract extends EntranceProfile{  
	private String absid; 	// 主键id
    private String title;  	// 新闻标题
    private String auth;	// 新闻来源
    private String originalDate;//新闻发布日期,原始样式
    private Date date;		//处理后的发布日期
    private String imgUuid; // 对该条新闻的唯一标识，用于获取对应的图片,md5(title+auth+originalDate)
    //---
    private String imgPath;				// 网络原始新闻图片访问路径
    private String imgPathOriginal;		// 本地原图访问路径
    private String imgPathCompressed;	// 本地压缩过的图片访问路径
    private String imgPathListView;		// 本地新闻列表显示的剪切过的图片访问路径
    private List<String> imglist;		// 新闻列表需要显示的图片路径list形式
    //---
    private String tableName;			//table name for abstract news store
    private String sequenceName;
    private Integer other;
	
	
	public String getImgUuid() {
		return imgUuid;
	}
	public void setImgUuid(String imgUuid) {
		this.imgUuid = imgUuid;
	}
	public String getImgPathOriginal() {
		return imgPathOriginal;
	}
	public void setImgPathOriginal(String imgPathOriginal) {
		this.imgPathOriginal = imgPathOriginal;
	}
	public String getImgPathCompressed() {
		return imgPathCompressed;
	}
	public void setImgPathCompressed(String imgPathCompressed) {
		this.imgPathCompressed = imgPathCompressed;
	}
	public String getImgPathListView() {
		return imgPathListView;
	}
	public void setImgPathListView(String imgPathListView) {
		this.imgPathListView = imgPathListView;
	}
	public String getSequenceName() {
		return sequenceName;
	}
	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public List<String> getImglist() {
		return imglist;
	}
	public void setImglist(List<String> imglist) {
		this.imglist = imglist;
	}
	
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	public String getOriginalDate() {
		return originalDate;
	}
	public void setOriginalDate(String originalDate) {
		this.originalDate = originalDate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTitle() {  
        return title;  
    }  
    public void setTitle(String title) {  
        this.title = title;  
    }  
   
	public String getAbsid() {
		return absid;
	}
	public void setAbsid(String absid) {
		this.absid = absid;
	}
	public Integer getOther() {
		return other;
	}
	public void setOther(Integer other) {
		this.other = other;
	}  
          
      
}  