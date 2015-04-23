package com.rhcheng.news.extract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rhcheng.news.ESun0769;

/**
 * advanced process
 * 
 * news source:
 * http://www.wtoutiao.com/
 * http://m.toutiao.com/
 * http://news.sina.cn/
 * http://baijia.baidu.com/
 * http://news.sun0769.com/
 * http://et.21cn.com/
 * 
 * 
 * 
 * http://3g.163.com/touch/news
 * http://m.k.sohu.com/
 * 
 * 
 * 
 * 
 * excepted images：
 * 	wangmeng.baidu.com
 *	ubmcmm.baidustatic.com
 *	img.emarbox.com
 *	alicdn.com
 *	taobaocdn.com
 *	mmcdn.cn
 *  img.northnews.cn/images/khdnr.jpg
 *
 * excepted net：
 * http://www.dzwww.com/ ——图片用的相对路径
 * 
 * 
 * @author RhCheng
 * @date   2014-9-10
 */
public class AdvanceProcess {
	private AdvanceProcess(){}
	private static AdvanceProcess ad;
	public static AdvanceProcess getInstance(){
		if(null==ad){
			ad = new AdvanceProcess();
		}
		return ad;
	}
	
	public static List<String> getImglist(String url) throws IOException{
		String content = TextExtract.getContent(url);
		System.out.println(content);
		List<String> imglist = TextExtract.getImgTag(content);
		for(String img:imglist){
			System.out.println(TextExtract.getSrcValue(img)+" "+TextExtract.getImgName(img));
			
		}
		return null;
		
		
	}
	
	public static void main(String[] args) throws IOException{
		//List<String> list = ESun0769.getImglist("http://news.sun0769.com/dg/headnews/201409/t20140917_4434458.shtml");
//		for(String li:list){
//			System.out.println(li);
//		}
	}
	
	
}
