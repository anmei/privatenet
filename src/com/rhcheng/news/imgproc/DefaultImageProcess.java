package com.rhcheng.news.imgproc;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import com.rhcheng.common.MyConstant;
import com.rhcheng.util.BaseWebUtil;
import com.rhcheng.util.file.FileInfo;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * image process
 * @author RhCheng
 * @date   2014-12-25
 */
public class DefaultImageProcess {
	
	/**
	 * 下载图片到本地服务器
	 * @author RhCheng
	 * @date 2014-12-25
	 * @param imgpath
	 * @return 本地原图访问地址
	 */
	public static FileInfo downLoadImage(String imgpath,FileInfo fileinfo) {
		byte[] imgbyte = getImageFromNetByUrl(imgpath);
		if(imgbyte!=null){
			fileinfo.setLocalUploadFilePath((String)BaseWebUtil.getServletContext().getAttribute(MyConstant.UPLOAD_PATH_ORIGINAL));
			fileinfo.setStoreFileName(fileinfo.getStoreFileName()+".png");
			writeImageToDisk(imgbyte,fileinfo);
			fileinfo.setRealFileName(fileinfo.getStoreFileName());
			return fileinfo;
		}else{
			return null;
		}
	}
	/**
	 * 压缩图片
	 * @author RhCheng
	 * @date 2014-12-25
	 * @param fileinfo
	 * @return
	 */
	public static FileInfo compressImage(FileInfo fileinfo){
		if(fileinfo != null){
			fileinfo.setWebAccessFilePath((String)BaseWebUtil.getServletContext().getAttribute(MyConstant.UPLOAD_PATH_COMPRESSED));
			fileinfo.setStoreFileName(fileinfo.getRealFileName());
			toSmallerPic(fileinfo, null, null, 0.2f);
			fileinfo.setLocalUploadFilePath((String)BaseWebUtil.getServletContext().getAttribute(MyConstant.UPLOAD_PATH_COMPRESSED));
			fileinfo.setRealFileName(fileinfo.getStoreFileName());
		}
		return fileinfo;
	}
	
	/**
	 * 剪切图片
	 * @author RhCheng
	 * @date 2014-12-25
	 * @param fileinfo
	 * @return
	 */
	public static FileInfo cutImagetoListview(FileInfo fileinfo){
		if(fileinfo!=null){
			fileinfo.setWebAccessFilePath((String)BaseWebUtil.getServletContext().getAttribute(MyConstant.UPLOAD_PATH_LISTVIEW));
			fileinfo.setStoreFileName(fileinfo.getRealFileName());
			cutImg(fileinfo,78,78,0.2f);
			fileinfo.setLocalUploadFilePath((String)BaseWebUtil.getServletContext().getAttribute(MyConstant.UPLOAD_PATH_LISTVIEW));
			fileinfo.setRealFileName(fileinfo.getStoreFileName());
		}
		return fileinfo;
	}
	
	
	/**
	 * 删除本地图片
	 * @author RhCheng
	 * @date 2014-12-29
	 * @param imguuid 图片文件名（不包含后缀）
	 */
	public static void deleteLocalImg(String imguuid){
		if(StringUtils.isNotBlank(imguuid)){
			File original = new File((String)BaseWebUtil.getServletContext().getAttribute(MyConstant.UPLOAD_PATH_ORIGINAL));
			File compress = new File((String)BaseWebUtil.getServletContext().getAttribute(MyConstant.UPLOAD_PATH_COMPRESSED));
			File listview = new File((String)BaseWebUtil.getServletContext().getAttribute(MyConstant.UPLOAD_PATH_LISTVIEW));
	//		File original = new File("D:\\myprogram\\apache6\\Tomcat 6.0\\webapps\\privatenet\\newsImages_compressed");
	//		File compress = new File("D:\\myprogram\\apache6\\Tomcat 6.0\\webapps\\privatenet\\newsImages_listview");
	//		File listview = new File("D:\\myprogram\\apache6\\Tomcat 6.0\\webapps\\privatenet\\newsImages_original");
			File[] files1 = original.listFiles(new FileFilterImpl(imguuid));
			File[] files2 = compress.listFiles(new FileFilterImpl(imguuid));
			File[] files3 = listview.listFiles(new FileFilterImpl(imguuid));
			List<File> allf = new ArrayList<File>();
			allf.addAll(Arrays.asList(files1));
			allf.addAll(Arrays.asList(files2));
			allf.addAll(Arrays.asList(files3));
			for(File file:allf){
				if(file!=null){
					file.delete();
				}
			}
		}
	}
	
	
	
	//----------------------------------------------------------------------------------
	
	
	 /**  
     * 根据地址获得数据的字节流  
     * @param strUrl 网络连接地址  
     * @return  
     */  
    public static byte[] getImageFromNetByUrl(String strUrl){   
    	HttpURLConnection conn = null;
    	InputStream inStream = null;
    	ByteArrayOutputStream outStream = null;
        try {
//        	System.out.println("url-------------------"+strUrl);
            URL url = new URL(strUrl);   
            conn = (HttpURLConnection)url.openConnection();   
            conn.setRequestMethod("GET");   
            conn.setConnectTimeout(10 * 1000);   
            inStream = conn.getInputStream();//通过输入流获取图片数据   
            outStream = new ByteArrayOutputStream();   
            byte[] buffer = new byte[1024];   
            int len = 0;   
            while( (len=inStream.read(buffer)) != -1 ){   
                outStream.write(buffer, 0, len);   
            }   
            byte res[] = outStream.toByteArray();
            return res;
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally{
        	try {
	        	if(inStream != null){
					inStream.close();
	        	}
	        	if(outStream != null){
					outStream.close();
	        	}
	        	if(conn != null){
	        		conn.disconnect();
	        	}
        	} catch (IOException e) {
				e.printStackTrace();
			} 
        }
        return null;   
    }   
    
    /**  
     * 将图片写入到磁盘  
     * @param img 图片数据流  
     * @param fileName 文件保存时的名称  
     */  
    public static void writeImageToDisk(byte[] b, FileInfo fileinfo){
    	FileOutputStream fops = null;
        try {   
            File file = new File(fileinfo.getLocalUploadFilePath()+fileinfo.getStoreFileName());   
            fops = new FileOutputStream(file);   
            fops.write(b);   
            fops.flush();   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally{
        	try{
        		if(fops != null){
        			fops.close();
        		}
        	}catch (Exception e) {
        		e.printStackTrace();
			}
        } 
    }
  
    /**
     * 切图，在图片中间按照给定大小切
     * note: 如果给定的切图大小比原图还大，则返回的图片大小与原图相同
     * @author RhCheng
     * @date 2014-12-25
     * @param fileinfo 图片信息
     * @param w 目标宽
     * @param h 目标高
     * @param per 图片质量百分比(0~1)
     */
    public static void cutImg(FileInfo fileinfo,Integer w,Integer h,float per){
    	Image src;
    	File temfile = null;
		try {
			src = javax.imageio.ImageIO.read(new File(fileinfo.getLocalUploadFilePath()+fileinfo.getRealFileName()));
	        int old_w=src.getWidth(null); //得到源图宽
	        int old_h=src.getHeight(null); //得到源图长
	        double oldper = (old_w*1.00)/(old_h*1.00);// 求出需要剪切后的图宽与高的比例
//	        double newper = (w*1.00)/(h*1.00);// 求出需要剪切后的图宽与高的比例
	        int w2 = old_w-w;
	        int h2 = old_h-h;
	        int new_w=0;
	        int new_h=0;
	        // 剪切之前先按照一定原则将其尺寸根据原图比例压缩，以使得剪切出来的效果最好
	        // 压缩后确保长或宽至少有一者是不用剪切的
	        if(w2<=h2){
	        	if(w2<=0){
	        		new_w=old_w;
	        		new_h=(int)Math.round(new_w/oldper);
	        	}else{
	        		new_w=w;
	        		new_h=(int)Math.round(new_w/oldper);
	        	}
	        }else{
	        	if(h2<=0){
	        		new_h=old_h;
	        		new_w=(int)Math.round(new_h * oldper);
	        	}else{
	        		new_h=h;
	        		new_w=(int)Math.round(new_h * oldper);
	        	}
	        }
	        String temfilename = Thread.currentThread().getName()+"_"+new Date().getTime()+"_temporary.png";
	        Thumbnails.of(fileinfo.getLocalUploadFilePath()+fileinfo.getRealFileName())
			.forceSize(new_w, new_h)
	        .toFile(fileinfo.getLocalUploadFilePath()+temfilename);
	        
	        // 针对压缩后的图片进行剪切
	        temfile = new File(fileinfo.getLocalUploadFilePath()+temfilename);
			Thumbnails.of(temfile)
//	        Thumbnails.of(fileinfo.getLocalUploadFilePath()+fileinfo.getRealFileName())
			.scale((double)(1.0))
			.sourceRegion(Positions.CENTER,w,h)
			.toFile(fileinfo.getWebAccessFilePath()+fileinfo.getStoreFileName());
			
			// 压缩图片质量大小
			FileInfo temFileinfo = new FileInfo();
			temFileinfo.setLocalUploadFilePath(fileinfo.getLocalUploadFilePath());
			temFileinfo.setRealFileName(fileinfo.getStoreFileName());
			temFileinfo.setWebAccessFilePath(fileinfo.getLocalUploadFilePath());
			temFileinfo.setStoreFileName(fileinfo.getStoreFileName());
			toSmallerPic(temFileinfo,null,null,per);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(temfile != null){
				temfile.delete();
			}
		}
    	
    }
    
        
        
    /**
    * 对图片质量或者大小压缩处理
    * note:如果宽度为null,则根据高度按比例缩放，反之亦然；如果宽、高都为空，则保持原图大小,如果宽高都有值，则强制按照给定的大小压缩，可能会导致图片变形
    * @param fileinfo 图片信息
    * @param w 目标宽
    * @param h 目标高
    * @param per 图片质量百分比(0~1)
    */
    public static void toSmallerPic(FileInfo fileinfo,Integer w,Integer h,float per){
        FileOutputStream newimage = null;
        try {
           Image src = javax.imageio.ImageIO.read(new File(fileinfo.getLocalUploadFilePath()+fileinfo.getRealFileName())); //构造Image对象
           int old_w=src.getWidth(null); //得到源图宽
           int old_h=src.getHeight(null); //得到源图长
           double scare = (old_w*1.00)/(old_h*1.00);// 原图宽与高的比例
           // 根据给定的宽or高按原图比例缩放
           if(w == null && h == null){
        	   w = old_w;
        	   h = old_h;
           }else if(w == null){
        	   w = (int)Math.round(h * scare);
           }else if(h == null){
        	   h = (int)Math.round(w / scare);
           }
           
//           double w2=(old_w*1.00)/(w*1.00);
//           double h2=(old_h*1.00)/(h*1.00);
//           int new_w=0;
//           int new_h=0; 
           //计算新图长宽
//    	   new_w=(int)Math.round(old_w/w2);
//    	   new_h=(int)Math.round(old_h/h2);
           
           int new_w=w;
           int new_h=h; 
           
    	   BufferedImage tag = new BufferedImage(new_w,new_h,BufferedImage.TYPE_INT_RGB);       
           //tag.getGraphics().drawImage(src,0,0,new_w,new_h,null); //绘制缩小后的图
           tag.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH),0,0,null);
           newimage=new FileOutputStream(fileinfo.getWebAccessFilePath()+fileinfo.getStoreFileName()); //输出到文件流
           JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
           JPEGEncodeParam jep=JPEGCodec.getDefaultJPEGEncodeParam(tag);
            /* 压缩质量 */
           jep.setQuality(per, true);
           encoder.encode(tag, jep);
           //encoder.encode(tag); //近JPEG编码
        } catch (IOException ex) {
        	  ex.printStackTrace(); 
        } finally{
        	try{
	        	if(newimage != null){
	        		newimage.close();
	        	}
        	}catch (Exception e) {
				e.printStackTrace();
			}
        }
    }

    
    
    
    public static void main(String args[]){
        /*Thumbnails.of("H:\\2TEST\\aa.jpg")  
        .forceSize(112, 78)
        .scale(0.5f)
        .outputQuality(0.5f)
        .outputFormat("png") 
        .toFile("H:\\2TEST\\a_1.png"); */
    	
//        FileInfo fileinfo = new FileInfo();
//        fileinfo.setLocalUploadFilePath("H:\\2TEST\\");
//        fileinfo.setRealFileName("b.jpg");
//        fileinfo.setWebAccessFilePath("H:\\2TEST\\");
//        fileinfo.setStoreFileName("a_1.png");
//        toSmallerPic(fileinfo,null,null,0.5f);
//        File temfile = new File(fileinfo.getLocalUploadFilePath()+fileinfo.getRealFileName());
//        System.out.println(temfile.length());
//        cutImg(fileinfo,80,80,0.5f);
        
    	
    	deleteLocalImg("abcd");
    	
        System.out.println("ok");
        
            
    } 
    
	
	
}
