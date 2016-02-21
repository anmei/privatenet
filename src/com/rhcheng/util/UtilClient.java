package com.rhcheng.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
/**
 * HttpClient tool
 * @author RhCheng
 * @version 2011-11-22
 *
 */
public class UtilClient {
	
	private static Logger log = Logger.getLogger(UtilClient.class);
	
	/**
	 * HttpClient post request
	 * @param urlStr
	 * 			request url
	 * @param parmap
	 * 			parameter
	 * @param charSet
	 * 			encode
	 * @return
	 */
	public static String sendPostRequest(String urlStr,Map<String, String> parmap, String charSet) {
		long begainTime = System.currentTimeMillis();
		HttpClient client = new HttpClient();
		// 表示用Post方式提交
		PostMethod method = new PostMethod(urlStr);
		// 设置超时时间 假如超时 则返回 ""
		client.getHttpConnectionManager().getParams().setConnectionTimeout(15*1000);
		// 设置请求头信息
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);
		method.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla");
		// 模拟登陆
		method.setRequestHeader("Cookie","JSESSIONID=D205E544E58143AE764B1200529F8F37");
		// 设置请求参数
		if (null != parmap && parmap.size() > 0) {
			Iterator it = parmap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> me = (Map.Entry) it.next();
				method.addParameter(me.getKey(), me.getValue() == null ? "":me.getValue());
			}
		}
		try {
			int status = client.executeMethod(method);
			System.out.println("------------->status: "+status);
			if (status == 200) {
				String rs = new String(method.getResponseBody(), charSet);
				System.out.println("Set-Cookie------------->"+method.getResponseHeader("Set-Cookie"));
				log.info("----->sendPostRequest....URL:"+urlStr+"----result:"+rs);
				return rs;
			}else{
				String rs = new String(method.getResponseBody(), charSet);
				return rs;
			}
		} catch (HttpException e) {
			log.error("------>sendPostRequest,Please check your provided http address....URL:"+urlStr+"------>Exception:"+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error("------>sendPostRequest,Please check your net....URL:"+urlStr+"------>Exception:"+ e.getMessage());
			e.printStackTrace();
		} finally {
			log.info("----->sendPostRequest....URL:"+urlStr+"---- use Times:"+(System.currentTimeMillis()-begainTime));
			method.releaseConnection();
		}
		return null;
	}
	
	/**
	 * HttpClient get request
	 * @param urlStr
	 * 			request url
	 * @param parmap
	 * 			parameter
	 * @param charSet
	 * 			encode
	 * @return
	 */
	public static String sendGetRequest(String urlStr, String charSet) {
		long begainTime = System.currentTimeMillis();
		HttpClient client = new HttpClient();
		// 表示用GET方式提交
		GetMethod method = new GetMethod(urlStr);
		// 设置超时时间 假如超时 则返回 ""
		client.getHttpConnectionManager().getParams().setConnectionTimeout(15*1000);
		// 编码
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);
	
		try {
			int status = client.executeMethod(method);
			System.out.println("------------->status: "+status);
			if (status == 200) {
				String rs = new String(method.getResponseBody(), charSet);
				log.info("----->sendPostRequest....URL:"+urlStr+"----result:"+rs);
				return rs;
			}else{
				String rs = new String(method.getResponseBody(), charSet);
				return rs;
			}
		} catch (HttpException e) {
			log.error("------>sendPostRequest,Please check your provided http address....URL:"+urlStr+"------>Exception:"+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error("------>sendPostRequest,Please check your net....URL:"+urlStr+"------>Exception:"+ e.getMessage());
			e.printStackTrace();
		} finally {
			log.info("----->sendPostRequest....URL:"+urlStr+"---- use Times:"+(System.currentTimeMillis()-begainTime));
			method.releaseConnection();
		}
		return null;
	}
	
	
	public static String post(String url,Map<String,String> formvalues,Map<String,String> headers,String charset){
		long begainTime = System.currentTimeMillis();
		HttpClient client = new HttpClient();
		// 表示用Post方式提交
		PostMethod method = new PostMethod(url);
		// 设置超时时间 假如超时 则返回 ""
		client.getHttpConnectionManager().getParams().setConnectionTimeout(15*1000);
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
		// 设置请求头信息
		if(null != headers){
			for(Map.Entry<String,String> entry : headers.entrySet()){
				method.setRequestHeader(entry.getKey(), entry.getValue());
			}
		}
		// 设置form表单请求参数
		if (null != formvalues && formvalues.size() > 0) {
			Iterator it = formvalues.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> me = (Map.Entry) it.next();
				method.addParameter(me.getKey(), me.getValue() == null ? "":me.getValue());
			}
		}
		try {
			int status = client.executeMethod(method);
			System.out.println("------------->status: "+status);
			if (status == 200) {
				String rs = new String(method.getResponseBody(), charset);
				System.out.println("------------->"+method.getResponseHeader("Set-Cookie"));
				log.info("----->sendPostRequest....URL:"+url+"----result:"+rs);
				return rs;
			}else{
				String rs = new String(method.getResponseBody(), charset);
				return rs;
			}
		} catch (HttpException e) {
			log.error("------>sendPostRequest,Please check your provided http address....URL:"+url+"------>Exception:"+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error("------>sendPostRequest,Please check your net....URL:"+url+"------>Exception:"+ e.getMessage());
			e.printStackTrace();
		} finally {
			log.info("----->sendPostRequest....URL:"+url+"---- use Times:"+(System.currentTimeMillis()-begainTime));
			method.releaseConnection();
		}
		return null;
	}
	
	public static String get(String urlStr,Map<String,String> headers,String charSet) {
		long begainTime = System.currentTimeMillis();
		HttpClient client = new HttpClient();
		// 表示用GET方式提交
		GetMethod method = new GetMethod(urlStr);
		// 设置超时时间 假如超时 则返回 ""
		client.getHttpConnectionManager().getParams().setConnectionTimeout(15*1000);
		// 编码
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);
		// 设置请求头信息
		if(null != headers){
			for(Map.Entry<String,String> entry : headers.entrySet()){
				method.setRequestHeader(entry.getKey(), entry.getValue());
			}
		}
		try {
			int status = client.executeMethod(method);
			System.out.println("------------->status: "+status);
			if (status == 200) {
				String rs = new String(method.getResponseBody(), charSet);
				log.info("----->sendPostRequest....URL:"+urlStr+"----result:"+rs);
				return rs;
			}else{
				String rs = new String(method.getResponseBody(), charSet);
				return rs;
			}
		} catch (HttpException e) {
			log.error("------>sendPostRequest,Please check your provided http address....URL:"+urlStr+"------>Exception:"+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error("------>sendPostRequest,Please check your net....URL:"+urlStr+"------>Exception:"+ e.getMessage());
			e.printStackTrace();
		} finally {
			log.info("----->sendPostRequest....URL:"+urlStr+"---- use Times:"+(System.currentTimeMillis()-begainTime));
			method.releaseConnection();
		}
		return null;
	}
	
	
}
