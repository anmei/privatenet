package com.rhcheng.util.string;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.nodes.Element;

import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.entity.NewsDetails;
import com.rhcheng.news.webmagic.BaseSpider;
import com.rhcheng.util.date.DateUtils;
import com.rhcheng.util.digest.EncryptUitls;

/**
 * news common utils
 * @author RhCheng
 * @date   2014-9-26
 */
public class NewsUtils {
	private NewsUtils(){}
	
	
	/**get title by {@code jsoup}*/
	public static String getTitle(Element e){
		return e.ownText().trim();
	}
	
	

	/** convert String to list*/
	public static List<String> string2List(String str){
		if(StringUtils.isNotBlank(str)){
			String[] a = str.split("\\|");
			List<String> res = new ArrayList<String>();
			for(int i=0;i<a.length;i++){
				if(StringUtils.isNotBlank(a[i])){
					res.add(a[i]);
				}
			}
			return res;
		}else return null;
		
	}

	
	public static String replaceSomeChar(String resstr){
		resstr=resstr.replace("width", "w");
		resstr=resstr.replace("height", "h");
		resstr=resstr.replace("WIDTH", "w");
		resstr=resstr.replace("HEIGHT", "h");
		resstr=resstr.replace("alt_src", "src");
		return resstr;
	}
	
	public static boolean ifNumbers(String str){
		String regex = "\\d{1,}";
		Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);  
        if(matcher.matches()){
        	return true;
        }else{
        	return false;
        }
	}
	
	public static String getImgUuid(NewsAbstract newsabs){
		if(StringUtils.isNotBlank(newsabs.getImgPath())){
			return EncryptUitls.MD5Digest((newsabs.getTitle()==null?"":newsabs.getTitle())
					+(newsabs.getAuth()==null?"":newsabs.getAuth())
					+(newsabs.getOriginalDate()==null?"":newsabs.getOriginalDate())
					+ new Date().getTime());
		}else{
			return null;
		}
	}
	
	
	
	//---------------------------------------------------------------------------------image process
	
	/**
	 * return all the image tag of news content
	 * @author RhCheng
	 * @date 2014-12-3
	 * @param str news content
	 * @return a list including all image tag
	 * if(null != value && Pattern.matches("([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})", value)) {
	 */
	public static List<String> getImgTag(String str){
		String regex = "(?=)<img.*?>";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);
        List<String> res = new ArrayList<String>();
        while(matcher.find()){
        	res.add(matcher.group());
        }
        return res;
	}
	
	/**
	 * get image src 
	 * @author RhCheng
	 * @date 2014-12-3
	 * @param str image tag format:{@code <img src="..." />}
	 * @return image src,format:{@code src="..."}
	 */
	public static String getImgSrc(String str){
		String regex = " src=\"(?<=).*?(?=)\"";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()){
        	return matcher.group();
        }
        return null;
	}
	
	/**
	 * get image src value
	 * @author RhCheng
	 * @date 2014-12-3
	 * @param str image tag
	 * @return src value,format: http://www.fds.fsd/ds.jpg
	 */
	public static String getSrcValue(String str){
		String src = getImgSrc(str);
		return src.substring(src.indexOf("\"")+1, src.length()-1);
	}	

	/**
	 * get image absolute path except image name
	 * @param url request url
	 * @return
	 */
	public static String getPrefixOfImg(String url){
		return url.substring(0, url.lastIndexOf("/")+1);
	}
	
	/**
	 * get image name
	 * @author RhCheng
	 * @date 2014-12-3
	 * @param str iamge tag
	 * @return image name
	 */
	public static String getImgName(String str){
		String src = getSrcValue(str);
		return src.substring(src.lastIndexOf("/")+1);
	}
	
	
	/**
	 * get the absolute src value
	 * @author RhCheng
	 * @date 2014-12-3
	 * @param url request url
	 * @param imgTag image tag, format:{@code <img src="..." />}
	 * @return
	 */
	public static String getImgAbsoluteSrcValue(String url,String imgTag){
		return getPrefixOfImg(url)+getImgName(imgTag);
	}
	
	
	
	//-----------------------------------------------------------------------------------news filter
	
	/**
	 * get Date String,the date must exact to minute at least or will return {@code null}
	 * the method can used when get news abstract normally.
	 * @author RhCheng
	 * @date 2014-12-3
	 * @param str
	 * @return
	 */
	public static String getDate(String str){
		String regex = "\\d{4}(-|年)\\d{1,2}(-|月)\\d{1,2}.*? \\d{1,2}(:|时)\\d{1,2}((:|分)\\d{1,2})?";
		Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);  
        if(matcher.find())  
            return matcher.group(0).replace('年', '-').replace('月', '-').replace("日", "")
            		.replace("时", ":").replace("分", ":")
            		.replaceAll("[^-\\d:]", " ").replaceAll(" +", " ");
        else  
            return null;  
	}
	
	/**
	 * get Date String, the date must exact to Day at least or will return {@code null}
	 * the method can used when get news detail normally.
	 * @author RhCheng
	 * @date 2014-12-3
	 * @param str
	 * @return
	 */
	public static String getDateInNewsDetail(String str){
		String regex1 = "\\d{4}(-|年)\\d{1,2}(-|月)\\d{1,2}.*? \\d{1,2}(:|时)\\d{1,2}(:|分)\\d{1,2}";
		Pattern pattern1 = Pattern.compile(regex1);  
        Matcher matcher1 = pattern1.matcher(str);
        
        String regex2 = "\\d{4}(-|年)\\d{1,2}(-|月)\\d{1,2}.*? \\d{1,2}(:|时)\\d{1,2}";
    	Pattern pattern2 = Pattern.compile(regex2);  
        Matcher matcher2 = pattern2.matcher(str);
        
        String regex3 = "\\d{4}(-|年)\\d{1,2}(-|月)\\d{1,2}.*? \\d{1,2}";
        Pattern pattern3 = Pattern.compile(regex3);  
        Matcher matcher3 = pattern3.matcher(str);
		
		String regex4 = "\\d{4}(-|年)\\d{1,2}(-|月)\\d{1,2}";
		Pattern pattern4 = Pattern.compile(regex4);  
        Matcher matcher4 = pattern4.matcher(str);  
        
        if(matcher1.find()){  
            return matcher1.group(0).replace('年', '-').replace('月', '-').replace("日", "")
            		.replace("时", ":").replace("分", ":")
            		.replaceAll("[^-\\d:]", " ").replaceAll(" +", " ");
        }else if(matcher2.find()){  
            return matcher2.group(0).replace('年', '-').replace('月', '-').replace("日", "")
            		.replace("时", ":").replace("分", ":")
            		.replaceAll("[^-\\d:]", " ").replaceAll(" +", " ");
        }else if(matcher3.find()){  
            return (matcher3.group(0).replace('年', '-').replace('月', '-').replace("日", "")
            		.replace("时", ":").replace("分", ":")
            		.replaceAll("[^-\\d:]", " ").replaceAll(" +", " "))+":00";
        }else if(matcher4.find()){  
            return (matcher4.group(0).replace('年', '-').replace('月', '-').replace("日", "")
            		.replace("时", ":").replace("分", ":")
            		.replaceAll("[^-\\d:]", " ").replaceAll(" +", " "))+" 00:00";
        }else{
        	return DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm");  
        }
		
	}
	
	
	/**
	 * date pattern must as follow or will return null
	 * yyyy-MM-dd HH:mm
	 * @author RhCheng
	 * @date 2014-9-17
	 * @param str
	 * @return
	 */
	public static Date parseDate(String date){
		if(date != null){
			return DateUtils.strToDate(date, "yyyy-MM-dd HH:mm");
		}else {
			return null;
		}
	}

	public static String formatFromMillSecond(Long millsecond){
		return DateFormatUtils.formatUTC(millsecond, "HH:mm:ss.SSS");
	}
	
	
	
	/**update interval, unit: ms*/
	public static long UPDATE_INTERVAL = 2 * 60 * 60 * 1000;
//	public static long UPDATE_INTERVAL = 24 * 60 * 60 * 1000;
	/**delete interval, unit: ms*/
	public static long DELETE_INTERVAL = 4 * 24 * 60 * 60 * 1000;
	
	public static long dateInterval(Date date){
		return new Date().getTime()-date.getTime();
	}
	
	/** interval smaller than {@code NewsUrls.INTERVAL}*/
	public static boolean ifok(Date date) {
		return dateInterval(date)<UPDATE_INTERVAL?true:false;
	}
	
	public static String toDeleteByDate(){
		return DateFormatUtils.format((new Date().getTime()-DELETE_INTERVAL),"yyyy-MM-dd HH:mm");
	}
	
	
	public static BaseSpider getInstanceByName(String className) throws InstantiationException, 
		IllegalAccessException, ClassNotFoundException{
		return (BaseSpider)Class.forName(className).newInstance();
	}
	
	
	/**
	 * filter and store the news list
	 * @author RhCheng
	 * @date 2014-11-28
	 * @param newsmap
	 * @param newsmapbyurl
	 * @param obj
	 * @param pageProcessor
	 */
	public static void checkAndPutNewsAbs(Map<String,List<NewsAbstract>> newsmap,Map<String,NewsAbstract> newsmapbyurl,
			Map<String,List<String>> allurls,List<NewsAbstract> allNewsList,
			NewsAbstract obj,String pageProcessor){
		if(checkNewsAbstact(obj,newsmapbyurl)){
			if(newsmap.containsKey(pageProcessor)){
				newsmap.get(pageProcessor).add(obj);
			}else{
				List<NewsAbstract> newList = new ArrayList<NewsAbstract>();
				newList.add(obj);
				newsmap.put(pageProcessor, newList);
			}
			
			if(allurls.containsKey(pageProcessor)){
				allurls.get(pageProcessor).add(obj.getUrl());
			}else{
				List<String> newurl = new ArrayList<String>();
				newurl.add(obj.getUrl());
				allurls.put(pageProcessor, newurl);
			}
			
			newsmapbyurl.put(obj.getUrl(), obj);
			allNewsList.add(obj);
			
		}
	}
	
	
	/**
	 * filter and store the news detail
	 * @author RhCheng
	 * @date 2014-12-2
	 * @param allNewsDetailList
	 * @param newsdet
	 * @param pubdate
	 */
	public static void checkAndPutNewsDetail(List<NewsDetails> allNewsDetailList,
			Map<String,NewsAbstract> allNewsByUrl,List<NewsAbstract> allNewsList,
			NewsDetails newsdet){
		if(checkNewsDetail(newsdet)){
			allNewsDetailList.add(newsdet);
		}
		
	}
	
	
	
	
	/**
	 * check and filter news abstract
	 * @author RhCheng
	 * @date 2014-12-2
	 * @param url
	 * @return
	 */
	public static boolean checkNewsAbstact(NewsAbstract obj,Map<String,NewsAbstract> newsmapbyurl){
		if(obj.getUrl().indexOf("、") != -1 ||
				(obj.getDate() != null && !ifok(obj.getDate())) ){
			return false;
		}else{
			for(Entry<String, NewsAbstract> ent:newsmapbyurl.entrySet()){
				if(ent.getValue().getUrl().equalsIgnoreCase(obj.getUrl()) || 
						ent.getValue().getTitle().equalsIgnoreCase(obj.getTitle())){
					return false;
				}
			}
			
			return true;
		}
	}
	
	/**
	 * check and filter news detail
	 * @author RhCheng
	 * @date 2014-12-2
	 * @param newsabs
	 * @param newsdet
	 * @return
	 */
	public static boolean checkNewsDetail(NewsDetails newsdet){
		if(newsdet.getDate()!=null && !NewsUtils.ifok(newsdet.getDate()) || 
				StringUtils.isBlank(newsdet.getContent())){
			return false;
		}else{
			return true;
		}
	}
	
	

	
	public static void main(String[] args){
		System.out.println(parseDate(getDate("2014年12月03日")));
	}
	
}
