package com.rhcheng.news.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;


public class CarViolationResult1 {

	private String resultCode;

	private Set<CarViolation> vioSet;

	private String urlPath = "http://146.32.3.158:8000/wfsjcx/wfsjcx.jsp?UserId=dg_post&UserPwd=dg_post&Source=web&Type=car&Content=";

	private int vioSize;

	public int getVioSize() {
		return this.vioSet.size();
	}
	

	
	//---------------------------------------------------------------------------------无距平台接口
	
	
	/**
	 * 
	 * @author RhCheng
	 * @date 2015-1-21
	 * @param carType
	 * @param carNumber
	 * @param carCode
	 * @throws UnsupportedEncodingException
	 */
	public void query2(String carType, String carNumber, String carCode){
//		String url = (String)WebUtil.application().getAttribute("wujuiniterface");
		String requestXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><header sysNo=\"tkywx\" interfaceCode=\"wuju\" /><params><param name=\"USERACCOUNT\" value=\"Z2QwMDEz\" /><param name=\"PASSWORD\" value=\"ZGd6eXl6MDE=\" /><param name=\"MDCODE\" value=\"QUJDRjAyNzI2MkRFRTlCMjc4OTQ3RThCRDEzRTJDNkUwQ0VDMjY2N0FGQTY3QTdGNzUzREY4ODQ=\" /><param name=\"PROCESSCODE\" value=\"V09PSklJX0FNRVJDRV9RVUVSWV8x\" /><param name=\"REQUEST\" value=\"null\" /></params></request>";
		
		
		String response="";
		try {
//			response = Jsoup.connect(url).data("requestXml", requestXml)
//					.ignoreContentType(true).timeout(10000).get().toString();
			
//			Map<String,String> para = new HashMap<String, String>();
//			para.put("requestXml",currequest);
//			response = sendPostRequest(url,para,"UTF-8");
			
//			Map<String,String> res2 = parseResult(url,requestXml);
			
			Map<String,String> res2 = parseResult("http://192.183.3.207:8888/msgbroker/msgBroker.do",requestXml);
			
			
		} catch (IOException e) {
			this.resultCode = "106";
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		new CarViolationResult1().query2("fs", "fds", "fsd");
	}
	
	public static String getCartype(String oricartype){
		if(oricartype.equalsIgnoreCase("小型汽车")){
			return "02";
		}else if(oricartype.equalsIgnoreCase("大型汽车")){
			return "01";
		}else if(oricartype.equalsIgnoreCase("轻型厢式货车")){
			return "01";
		}else if(oricartype.equalsIgnoreCase("外籍汽车")){
			return "06";
		}else{
			return "";
		}
	}
	
	
	

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
		// 设置超时时间 假如超时 则返回 ""
		 client.getHttpConnectionManager().getParams().setConnectionTimeout(15*1000);
		// 表示用Post方式提交
		PostMethod method = new PostMethod(urlStr);
		// 编码
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);
		method.getParams().setParameter(HttpMethodParams.USER_AGENT, "Mozilla");
		
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
			if (status == 200) {
				String rs = new String(method.getResponseBody(), charSet);
				return rs;
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return null;
	}
	
	
	
	/*
	 * parse result getted from dewen interface
	 */
	public static Map<String,String> parseResult(String url,String para) throws IOException{
		String res = Jsoup.connect(url).data("requestXml", para).userAgent("Mozilla")
				.ignoreContentType(true).timeout(10000).get().toString();
		
		
		/*URL url1;  
        URLConnection urlconn;  
        String res = "";
        try {  
            url1 = new URL("http://www.baidu.com");  
            urlconn = url1.openConnection();  
            HttpURLConnection httpConnection = (HttpURLConnection) urlconn;  
            httpConnection.setConnectTimeout(10000);  
            httpConnection.setReadTimeout(10000);  
//	          httpConnection.setRequestProperty("User-Agent", "new");  
	        httpConnection.setRequestMethod("GET");
//	        httpConnection.setRequestProperty("requestXml", para);
            InputStream in = httpConnection.getInputStream();  
            BufferedReader br = new BufferedReader(new InputStreamReader(in));  
            String line = "";  
            while((line = br.readLine()) != null) {  
            	res += line;  
            }  
            br.close();  
            in.close();  
            System.out.println(res+"----fsd");
        } catch(IOException e) {  
            e.printStackTrace();  
        }  */
		
		
		
		System.out.println(res);
		String res1 = res.substring(res.indexOf('{', 0), res.lastIndexOf('}')+1).replaceAll("&quot;", "\"");
		return jsonStrToMap(res1);
	}
	
	
	/*
	 *JsonStr->Map
	 */
	public static Map<String, String> jsonStrToMap(String jsonStr) {
		if (StringUtils.isBlank(jsonStr)) {
			return null;
		}
		JSONObject json = JSONObject.fromObject(jsonStr);
		Iterator<String> iterator = json.keys();
		Map<String, String> result = new HashMap<String, String>();
		String key = null;
		String value = null;
		while (iterator.hasNext()) {
			key = iterator.next();
			value = json.getString(key);
			if (StringUtils.isBlank(value)) {
				value = null;
			}
			result.put(key, value);
		}
		return result;
	}
	
	
	
	public static class CarViolation implements Comparable<CarViolation> {
		// 违章代码??
		private String vialationCode;
		// 违章内容
		private String vialationContent;
		// 违章时间
		private String vialationTime;
		// 违章地点
		private String vialationPlace;
		// 违章费用
		private double vialationFee;
		//		??
		private String vialationSign;
		// 违章滞纳金
		private double vialationLateFee;

		private int status = 0;

		public int compareTo(CarViolation o) {
			return this.getVialationTime().compareTo(o.vialationTime);
		}

		public String getVialationCode() {
			return vialationCode;
		}

		public void setVialationCode(String vialationCode) {
			this.vialationCode = vialationCode;
		}

		public String getVialationContent() {
			return vialationContent;
		}

		public void setVialationContent(String vialationContent) {
			this.vialationContent = vialationContent;
		}

		public String getVialationTime() {
			return vialationTime;
		}

		public void setVialationTime(String vialationTime) {
			this.vialationTime = vialationTime;
		}

		public String getVialationPlace() {
			return vialationPlace;
		}

		public void setVialationPlace(String vialationPlace) {
			this.vialationPlace = vialationPlace;
		}

		public double getVialationFee() {
			return vialationFee;
		}

		public void setVialationFee(double vialationFee) {
			this.vialationFee = vialationFee;
		}

		public String getVialationSign() {
			return vialationSign;
		}

		public void setVialationSign(String vialationSign) {
			this.vialationSign = vialationSign;
		}

		public double getVialationLateFee() {
			return vialationLateFee;
		}

		public void setVialationLateFee(double vialationLateFee) {
			this.vialationLateFee = vialationLateFee;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public Set<CarViolation> getVioSet() {
		return vioSet;
	}

	public void setVioSet(Set<CarViolation> vioSet) {
		this.vioSet = vioSet;
	}

	
	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}



}