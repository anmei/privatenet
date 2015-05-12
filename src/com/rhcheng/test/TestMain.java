package com.rhcheng.test;

import java.applet.AppletContext;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.Application;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import com.rhcheng.user.entity.User;


public class TestMain {
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException{
		
//		int a;
//		try{
//			a=0;
//		}catch(Exception e ){
//			a=1;
//		}finally{
//			a=2;
//		}
//		System.out.println(a);
		
		/**初始化容器**/
		ApplicationContext ac = new ClassPathXmlApplicationContext("/configure/applicationContext.xml");
//		TestDI td = ac.getBean("testDI", TestDI.class);
//		td.print();
		
		TestAnotationDI td = ac.getBean("testAnotationDI", TestAnotationDI.class);
		td.print();
		
		
//		User user=new User(); //Java Object
//		user.setName("hah虎");
//		user.setAddress("fds");
//		user.setUserid(1L);
//		user.setBirthday(new Date());
//		
//		Map<String,Object> m = new HashMap<String,Object>();
//		m.put("a", "abc");
//		m.put("b", user);
//		
//		List<Object> li = new ArrayList<Object>();
//		li.add(m);
//		li.add(user);
//		li.add("fsd");
//
//		ObjectMapper mapper = new ObjectMapper();
//		/** 时间格式等配置*/
//		mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
//		mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));  
//		
//		String jsonstr = mapper.writeValueAsString(li); //返回字符串
//		System.out.println(jsonstr);
//		
//		List ma = mapper.readValue(jsonstr, ArrayList.class);
//		
//		Map a = (Map)ma.get(0);
//		/** POJO也转为map基本类型了*/
//		Map b = (Map)a.get("b");
//		System.out.println(b.get("birthday")+" "+b.get("name"));
		
		
		
	}
	
}
