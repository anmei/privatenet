package com.rhcheng.util.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;

import com.rhcheng.common.SysConstants;
    

/**
 * 上传文件工具
 * @author RhCheng
 * @date   2014-7-29
 */
public class FileUploadUtil {
    
    /**
     * apache common 工具上传文件
     * @author RhCheng
     * @date 2014-6-24
     * @param request
     * @return {@ List} in which are {@ FileInfo}
     * @throws Exception
     */
    public static List<FileInfo> uploadFile(HttpServletRequest request) throws Exception{
//    	System.out.println(request.getServletContext().getRealPath("/"));
//    	String curProjectPath = request.getServletContext().getRealPath("/");  
//      String saveDirectoryPath = curProjectPath + File.separator + uploadFolderName;  
//      String tempDirectoryPath = curProjectPath + File.separator + tempFolderName;  
    	String localFileUploadPath = request.getRealPath("/")+SysConstants.uploadPath;
    	String tempFileUploadPath = request.getRealPath("/")+SysConstants.tempPath;
        File localSaveDirectory = new File(localFileUploadPath);  
        File localTempDirectory = new File(tempFileUploadPath);
        
        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //Set factory constraints
        //DiskFileItemFactory中DEFAULT_SIZE_THRESHOLD=10240表示如果上传文件大于10K则会产生上传临时文件  
        //factory.setSizeThreshold(yourMaxMemorySize);
        if(!localSaveDirectory.exists()) {  
        	localSaveDirectory.mkdirs();  
        }  
        if(!localTempDirectory.exists()) {  
        	localTempDirectory.mkdirs();  
        }  
        
        //上传临时文件的默认目录为java.io.tmpdir中保存的路径，根据操作系统的不同会有区别  
        //重新设置临时文件保存目录  
        factory.setRepository(localTempDirectory);  

        
        ServletFileUpload upload = new ServletFileUpload(factory);  

        // 设置文件上传的大小限制  
        upload.setFileSizeMax(SysConstants.fileMaxSize);  

        // 设置文件上传的头编码，如果需要正确接收中文文件路径或者文件名  
        // 这里需要设置对应的字符编码，为了通用这里设置为UTF-8  
        upload.setHeaderEncoding(SysConstants.encode);  
        
        List<FileInfo> res = new ArrayList<FileInfo>();
        
        //解析请求数据包  
        List<FileItem> fileItems = upload.parseRequest(request);
        //遍历解析完成后的Form数据和上传文件数据  
        for (Iterator<FileItem> iterator = fileItems.iterator(); iterator.hasNext();) {  
        	FileInfo fileinfo = new FileInfo();
        	
            FileItem fileItem = iterator.next();  
            //如果为上传文件数据  
            if(!fileItem.isFormField()) {  
//            	String fieldName = fileItem.getFieldName();// 表单字段名  
            	String name = fileItem.getName();// 文件名
                String realfileName = FilenameUtils.getName(name);  
                String fileExtension = FilenameUtils.getExtension(name);
//            	String contentType = fileItem.getContentType();
//              boolean isInMemory = fileItem.isInMemory();
//              long sizeInBytes = fileItem.getSize();
            	
                if(fileItem.getSize() > 0) {  
                    if(!ArrayUtils.contains(SysConstants.extensionPermit, fileExtension)) {  
                        System.out.println("该扩展名文件不允许上传");
                        fileinfo.setResMes("error,exceed the limit");
                        res.add(fileinfo);
                        return res;
                    }  
                    
                    fileinfo.setRealFileName(realfileName);
                    fileinfo.setFileSuffix(fileExtension);
                    fileinfo.setStoreFileName(UUID.randomUUID().toString()+"."+fileExtension);
                    fileinfo.setWebAccessFilePath(SysConstants.uploadPath+fileinfo.getStoreFileName());
                    fileinfo.setLocalUploadFilePath(localFileUploadPath);
                    fileinfo.setTempUploadFilePath(tempFileUploadPath);
                    
//                    FileUtils.copyInputStreamToFile(fileItem.getInputStream(),new File(localSaveDirectory, fileinfo.getStoreFileName()));// 读取到流
//                    byte[] data = fileItem.get();
                    fileItem.write(new File(localSaveDirectory, fileinfo.getStoreFileName()));// 读取到内存
                    
                    fileinfo.setResMes("success");
                    
                    
                }  
                
            } else { //Form表单数据  
            	
            	String fieldName = fileItem.getFieldName();// 表单字段名  
                String value = fileItem.getString("UTF-8"); // 表单字段值
                fileinfo.setResMes("error");
            }  
            res.add(fileinfo);
            
        }
        
       return res;

        
    }
    
    
    public static void main(String[] args) throws IOException{
    	
    	File file = new File("h:\\2TEST\\abc\\bvd");
    	if(!file.exists()){
    		file.mkdirs();
    	}
    	File file2 = new File(file,"aaa.txt");
    	
    	file2.createNewFile();
    	
    	
    	
    }
    
	
}
