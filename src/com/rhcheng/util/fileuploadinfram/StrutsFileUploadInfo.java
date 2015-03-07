package com.rhcheng.util.fileuploadinfram;

import java.io.InputStream;

/**
 * Struts 上传文件信息实现
 * @author flatychen
 * @date 2014-4-21
 */
public class StrutsFileUploadInfo implements UploadInfo {

	@Override
	public long getFileSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getFileContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOriginalFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFormName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWebRootPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
