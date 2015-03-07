
package com.rhcheng.news.extract;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 
 * @author anmei
 * @date   2014-9-9
 */
public class UseDemo {
	
	//---------------------------------------------------------------------static class method
	
	/**
	 * get the original html document by {@code strURL}, will use the right encode auto
	 * @param strURL
	 * @return
	 * @throws IOException
	 */
	public static String getHTML(String strURL) throws IOException {
		URL url = new URL(strURL);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),getChartSetOfWeb(strURL)));
		String s = "";
		StringBuilder sb = new StringBuilder("");
		while ((s = br.readLine()) != null) {
			sb.append(s + "\n");
		}
		if(br!=null){
			br.close();
		}
		return sb.toString();
	}
	
	/**
	 * get the original html document by {@code strURL} and {@code charset}
	 * @author RhCheng
	 * @date 2014-9-9
	 * @param strURL
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String getHTML(String strURL,String charset) throws IOException {
		URL url = new URL(strURL);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),charset));
		String s = "";
		StringBuilder sb = new StringBuilder("");
		while ((s = br.readLine()) != null) {
			sb.append(s + "\n");
		}
		if(br!=null){
			br.close();
		}
		return sb.toString();
	}
	
	/**
	 * get the original html document by jsoup
	 * @author RhCheng
	 * @date 2014-9-9
	 * @param strURL
	 * @return
	 * @throws IOException
	 */
	public static Document getHTMLByJsoup(String strURL) throws IOException{
		Document doc = Jsoup.connect(strURL)
				.data("jquery", "java")
				.userAgent("Mozilla")
				.cookie("auth", "token")
				.timeout(60000)
				.get();
//		System.out.println(doc.toString());
		return doc;
		
	}
	
	
	
	 /** 
     * get common charset by {@code content}
     * @param content 
     * @return 
     */  
    private static String getCharSet(String content){  
    /*	String regex = ".*charset=([^;]+)(?=\")";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(content);  
        if(matcher.find())  
            return matcher.group(1);  
        else  
            return null;  */
    	content = content.toLowerCase();
    	if(content.contains("gb2312")){
    		return "gb2312";
    	}else if(content.contains("utf-8")){
    		return "utf-8";
    	}else if(content.contains("gbk")){
    		return "gbk";
    	}else if(content.contains("iso8859-1")){
    		return "iso8859-1";
    	}else {
    		return null;
    	}
    	
    }  
    
    /**
     * get the right charset of html document
     * note: the method will return UTF-8 if not find the right charset from the html documet
     * @author RhCheng
     * @date 2014-9-9
     * @param strURL
     * @return the right charset
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    public static String getChartSetOfWeb(String strURL) throws UnsupportedEncodingException, IOException{
    	URL url = new URL(strURL);
		String realencode="UTF-8";
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),realencode));
		String s = "";
		while ((s = br.readLine()) != null) {
			realencode = getCharSet(s);
			if(realencode != null){
//				System.out.println(realencode);
				return realencode;
			}
		}
		if(br!=null){
			br.close();
		}
		return realencode;
    }
    
	
    //--------------------------------------------------------------------------static main method
    
	public static void main(String[] args) throws IOException {
//		String content = getHTML("http://news.qq.com/a/20140909/015951.htm?tu_biz=1.114.1.0");
		String content = getHTMLByJsoup("http://news.qq.com/a/20140909/015951.htm?tu_biz=1.114.1.0").toString();
		
		System.out.println(content);
//		System.out.println(TextExtract.parse(content));
//		TextExtract.parse(content);
		
	}

    
	
}
