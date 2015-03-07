package com.rhcheng.util.fileuploadinfram;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StopWatch;

/**
 * 本地文件系统写入器
 * @author Administrator
 *
 */
public class LocalFileWriter implements FileWriter {
	

	@Override
	public UploadResult write(UploadInfo fileInfo, UploadFileRepo repo) {
		UploadResult ur = null;
		ur = this.initBasicUploadResult(fileInfo, repo);
		StopWatch  watch = new StopWatch();
		watch.start("上传文件");
		// 使用分段写入,防止内存溢出
		byte buffer[] = new byte[256 * 1024];
		File file;
		if(null != ur.getWebLinkfileName()){
			file = new File(FilenameUtils.normalize(fileInfo.getWebRootPath() + ur.getWebLinkfileName()));
			//System.out.println(FilenameUtils.normalize(fileInfo.getWebRootPath() + ur.getWebLinkfileName()));
		}else if(null != ur.getAbsoluteFileName()){
			file = new File(FilenameUtils.normalize(ur.getAbsoluteFileName()));
			//System.out.println(FilenameUtils.normalize(ur.getAbsoluteFileName()));
		}else{
			ur.setSuccess(false);
			ur.setWebLinkfileName("");
			ur.setMessage("======>>文件上传失败!,没有指定上传路径"); 
			return ur;
		}
		InputStream in = null;
		OutputStream out = null;
		try {
			out = FileUtils.openOutputStream(file);
			in = fileInfo.getInputStream();
			// 写入文件
			while ((in.read(buffer)) != -1) {
				out.write(buffer);
			}
			in.close();
			out.close();
			ur.setSuccess(true);
		} catch (IOException e) {
			ur.setSuccess(false);
			ur.setWebLinkfileName("");
			ur.setMessage("======>>文件上传失败!" + e.getMessage());
		}finally{
			fileInfo = null;
		}
		watch.stop();
		return ur;
	}

	private UploadResult initBasicUploadResult(UploadInfo uploadInfo,
			UploadFileRepo repo) {
		UploadResult ur = new UploadResult();
		ur.setRealFileName(uploadInfo.getOriginalFileName());
		ur.setUploadFormName(uploadInfo.getFormName());
		Date nowDay = new Date();
		String fileName = DigestUtils.md5Hex(nowDay.getTime() + "");
		ur.setFileNameInServer(fileName+"."+ ur.getFileExtName());
		if(repo.getRelativeUploadPath() != null){
			String relativeWebRootPathFile = repo.getRelativeUploadPath()
					+ File.separator
					+(fileName + "." + ur.getFileExtName());
			ur.setWebLinkfileName(relativeWebRootPathFile);
		}else if(repo.getAbsoluteUploadPath() != null){
			String absolutePath = repo.getAbsoluteUploadPath()+File.separator+(fileName + "." + ur.getFileExtName());
			ur.setAbsoluteFileName(absolutePath);
		}
		return ur;
	}
}
