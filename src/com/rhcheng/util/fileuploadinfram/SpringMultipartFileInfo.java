package com.rhcheng.util.fileuploadinfram;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * Spring 上传的文件信息实现类
 * @author Administrator
 *
 */
public class SpringMultipartFileInfo implements UploadInfo {
	
	private MultipartFile file;
	
	private String webRootPath;
	
	public SpringMultipartFileInfo(MultipartFile file,String webRootPath) {
		super();
		this.file = file;
		this.webRootPath = webRootPath;
	}
	
	
	
	@Override
	public long getFileSize() {
		return file.getSize();
	}

	@Override
	public String getFileContentType() {
		return file.getContentType();
	}

	@Override
	public String getOriginalFileName() {
		return file.getOriginalFilename();
	}

	@Override
	public String getFormName() {
		return file.getName();
	}

	@Override
	public boolean isEmpty() {
		return file.isEmpty();
	}

	@Override
	public InputStream getInputStream() {
//		BufferedInputStream bis = null;
//		try {
//			bis = new BufferedInputStream(file.getInputStream());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return bis;
		
		
		try {
			return file.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getWebRootPath() {
		return webRootPath;
	}

}
