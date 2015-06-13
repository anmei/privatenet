package com.rhcheng.test.testapi;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import com.rhcheng.util.UtilClient;
import com.rhcheng.util.digest.SignUtils;
/**
 * 如果不加User-Agent，则有些网址的访问，会被禁止的
 * 如果程序中得不到cookie（因此无法模拟登陆）可以看看是否是 内部自动跳转 auto redirect 而导致 无法获得cookie   
 * 禁止掉自动跳转，即设置autoredirect为false，通过代码去手动实现自动跳转，如此，跳转期间所产生的cookie，就可以得到了。
 * 
 * 
 * @author RhCheng
 * @date   2015年6月9日
 */
public class TestHttpClient {
	Map<String, String> map = new HashMap<String, String>();
//	String baseUrl = "http://192.183.3.163:8080/zhyd_admin/";
//	String baseUrl = "http://admin.uyuedu.com/";
//	String baseUrl = "http://interface.uyuedu.com:80/";
	String baseUrl = "http://dooland.uyuedu.com/";
	
	
	@Before
	public void setUp(){
		
	}
	
	//--------------------------------------------------------------------------utils
	@Test
	public void test0(){
		Map<String,String> a = SignUtils.string2map("address=%E5%B9%BF%E5%B7%9E%E5%B8%82%E5%A4%A9%E6%B2%B3%E5%8C%BA%E9%BE%99%E6%80%A1%E8%B7%AF117%E5%8F%B7%E9%93%B6%E6%B1%87%E5%A4%A7%E5%8E%A623%E5%B1%82%20&url=www.dooland.com&color=#f00e0e&subColor=#d49b83&media.url=http%3A%2F%2Fdooland.uyuedu.com%3A80%2Fupload%2Fapps%2F20150528%2F3679dea7c81fc8b4779548e15230b19e.jpg&media.w=278&media.h=150&industry=23&industry=20&industry=3&industry=19");
		for(Entry<String, String> en:a.entrySet()){
			System.out.println(en.getKey()+"="+en.getValue());
		}
		
	}
	
	//--------------------------------------------------------------------------test
	
	@Test
	public void test1(){
		String url = baseUrl+"company/addCompany.action";
		map.put("company.name", "domain2");
		map.put("company.domain", "domain2");
		map.put("company.cards", "10");
		map.put("oendDate", "2017-06-17");
		map.put("company.admin", "domain2");
		map.put("company.adminPwd", "123456");
		
		System.out.println(UtilClient.sendPostRequest(url, map, "utf-8"));
		
	}
	
	/**
	 * 模拟登陆 返回cookie，根据cookie模拟登陆
	 * @author RhCheng
	 * @date 2015年6月10日
	 */
	@Test
	public void test2(){
		String url = baseUrl+"admin/login";
		map.put("needCaptha", "false");
		map.put("loginName", "system");
		map.put("password", "123456");
		
		System.out.println(UtilClient.sendPostRequest(url, map, "utf-8"));
		
	}
	
}
