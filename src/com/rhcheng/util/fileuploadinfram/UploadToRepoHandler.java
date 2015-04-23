package com.rhcheng.util.fileuploadinfram;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 上传文件核心处理器<Br />
 * <p>
 * 使用时在控制器注入
 * </p>
 * 
 * @date 2014-4-21
 */
public class UploadToRepoHandler extends UploadFileSupport {

	/**
	 * 文件仓库
	 */
	private UploadFileRepo repo;

	/**
	 * 文件写入器接口
	 */
	private FileWriter localFileWriter;
	@Autowired
	public void setLocalFileWriter(FileWriter localFileWriter) {
		this.localFileWriter = localFileWriter;
	}

	public void setRepo(UploadFileRepo repo) {
		this.repo = repo;
	}

	/**
	 * 上传单个文件
	 * 
	 * @date 2014-4-21
	 * @param file
	 *            上传文件信息 {@link UploadInfo}
	 * @return
	 */
	public UploadResult uploadFile(UploadInfo file,UploadFileRepo ufr) {
		this.setRepo(ufr);
		// 检查文件上传是否合法,主要为文件大小与文件类型
		UploadResult upt = new UploadResult();
		if (!checkFileSize(file, repo)) {
			upt.setSuccess(false);
			upt.setMessage(repo.getCheckFileSizeMessage());
			return upt;
		}
		if (!checkContentType(file, repo)) {
			upt.setSuccess(false);
			upt.setMessage(repo.getCheckContentTypeMessage());
			return upt;
		}

		UploadResult ur = localFileWriter.write(file, repo);
		return ur;
	}

	/**
	 * 批量上传多个文件
	 * 
	 * @date 2014-4-21
	 * @param files
	 *            上传文件信息 {@link UploadInfo}
	 * @return
	 */
	public List<UploadResult> uploadFiles(UploadInfo files[],UploadFileRepo ufr) {
		List<UploadResult> uploadResults = new ArrayList<UploadResult>();
		for (int i = 0; i < files.length; i++) {
			UploadResult uploadResult = uploadFile(files[i],ufr);
			if (uploadResult != null) {
				uploadResults.add(uploadResult);
			}
		}
		return uploadResults;
	}
	
	
	

}
