package com.rhcheng.util.fileuploadinfram;

import org.apache.commons.lang.StringUtils;

public class UploadFileSupport {
	
	/**
	 * 检查文件类型与文件大小
	 * @param mfile
	 * @param fileRepo
	 * @return
	 */
	protected boolean checkContentType(UploadInfo mfile, UploadFileRepo fileRepo) {
		//System.out.println(mfile.getFileContentType());
		if (StringUtils.isBlank(fileRepo.getAllowTypes()))
			return true;
		if (mfile.getFileContentType() != null) {
			String types[] = fileRepo.getAllowTypes().split(",");
			for (int i = 0; i < types.length; i++) {
				if (StringUtils.equalsIgnoreCase(mfile.getFileContentType(),
						types[i])) {
					return true;
				}
			}
		}
		return false;
	}

	
	/**
	 * 检查文件大小匹配
	 * 
	 * @author flatychen
	 * @date 2014-4-18
	 * @param mfile
	 * @return
	 */
	protected boolean checkFileSize(UploadInfo mfile, UploadFileRepo fileRepo) {
		//System.out.println(mfile.getFileSize());
		if (!mfile.isEmpty()) {
			if (fileRepo.getAllowFileSize() == UploadFileRepo.UNLIMIT
					|| mfile.getFileSize() <= fileRepo.getAllowFileSize()) {
				return true;
			} else {
				return false;
			}
		}else{
			return false;
		}
	}
	
	

}
