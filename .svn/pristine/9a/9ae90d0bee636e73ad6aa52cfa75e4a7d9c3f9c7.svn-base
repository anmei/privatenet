package com.rhcheng.user.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rhcheng.common.SysConstants;
import com.rhcheng.util.fileuploadinfram.SpringMultipartFileInfo;
import com.rhcheng.util.fileuploadinfram.UploadFileRepo;
import com.rhcheng.util.fileuploadinfram.UploadInfo;
import com.rhcheng.util.fileuploadinfram.UploadResult;
import com.rhcheng.util.fileuploadinfram.UploadToRepoHandler;


@Controller
@RequestMapping(value="/fileupload/*")
public class FileUpload {
	
	private UploadToRepoHandler uploadFileHandler;
	@Autowired
	public void setUploadFileHandler(UploadToRepoHandler uploadFileHandler) {
		this.uploadFileHandler = uploadFileHandler;
	}


	/**
	 * 上传文件
	 * @date 2014-4-21
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String upload() {
		return "user/upload";
	}
	
	/**
	 * 显示上传结果
	 * @date 2014-4-21
	 * @return
	 */
	@RequestMapping(value = "/shows")
	public String shows(HttpServletRequest request,Model model) {
		model.addAttribute("data",request.getAttribute("data"));
		return "user/shows";
	}
	
	

	/**
	 * 上传单个文件处理
	 * @date 2014-4-21
	 * @param request
	 * @param files
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(HttpServletRequest request,@RequestParam("fileName") MultipartFile[]  files,Model model) {
		UploadInfo[] uploadInfos = new UploadInfo[files.length];
		for (int i = 0; i < uploadInfos.length; i++) {
			uploadInfos[i] = new SpringMultipartFileInfo(files[i],request.getContextPath());
		}
		// 上传文件
		UploadFileRepo ufr = new UploadFileRepo();
		ufr.setAbsoluteUploadPath("E:/program/apache-tomcat-7.0.42/webapps/website20140521/upload");// 设置文件上传的绝对路径
		List<UploadResult> ups = uploadFileHandler.uploadFiles(uploadInfos,ufr);
		
		//model.addAttribute(ups);
		model.addAttribute("data",ups);
		return "user/shows";

	}

	
	/**
	 * 一个文件表单上传多个处理
	 * @author flatychen
	 * @date 2014-4-21
	 * @param request
	 * @param files
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/creates", method = RequestMethod.POST)
	public String creates(HttpServletRequest request,@RequestParam("fileName") MultipartFile[]  files,Model model) {
		UploadInfo[] uploadInfos = new UploadInfo[files.length];
		for (int i = 0; i < uploadInfos.length; i++) {
			uploadInfos[i] = new SpringMultipartFileInfo(files[i],request.getContextPath());
		}
		// 上传文件
		UploadFileRepo ufr = new UploadFileRepo();
		ufr.setAbsoluteUploadPath("E:/program/apache-tomcat-7.0.42/webapps/website20140521/upload");
		List<UploadResult> ups = uploadFileHandler.uploadFiles(uploadInfos,ufr);

		model.addAttribute("data",ups);
		return "user/shows";

	}
	
}
