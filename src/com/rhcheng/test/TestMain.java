package com.rhcheng.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

/*
 * java.io
 * 
 * InputStream\OutputStream（操作字节数组）\Reader\Writer（操作字符数组） —— 好比是连接器，使得用户能够方便地与File等交互.
 * 连接器模型：
 * |----------------------------|
 * |			 |				|
 * |		File | Writer		|
 * |			 |				|
 * |OutputStream | Writer		|
 * |			 |				|
 * |	Buffered | Writer		|
 * |			 |				|
 * |		File | OutputSteram	|
 * |			 |				|
 * |	Buffered | OutputStream	|
 * |			 |				|
 * | InputStream | Reader		|
 * |			 |				|
 * |	 ……		 |	……			|
 * |----------------------------|
 * 
 * 数组扩容、装饰器模式
 * 不论是字节还是字符类型，底层最终操作的都是字节
 * 
 * 字节：
 * FileInputStream\FileOutputStream\ObjectOutputStream\ObjectInputStream\ByteArrayInputStream\ByteArrayOutputStream
 * 字符：
 * FileReader\FileWriter\CharArrayReader\CharArrayWriter\InputStreamReader\OutputStreamWriter\
 * 缓冲：
 * BufferedInputStream\BufferedOutputStream\BufferedReader\BufferedWriter\
 * 
 * commons.io.IOUtils中的思想：先转换为CharArrayWriter\ByteArrayOutputStream\StringWriter，然后再进一步操作。其中基本涵盖了所有可能情况
 * 
 */


public class TestMain {
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException{
		
		System.out.println(System.getProperty("sun.arch.data.model"));
		
//		System.out.println(IOUtils.toString(new FileInputStream("H:\\2T_E_S_T\\c.jpg")));
		
//		StringWriter s = new StringWriter();
//		PrintWriter p = new PrintWriter(s);
//		p.print("fdsa发大水发松岛枫");
//		System.out.println(s.toString());
		
//		BufferedInputStream b = new BufferedInputStream(new FileInputStream("H:\\2T_E_S_T\\c.jpg"));
//		File f = new File("H:\\2T_E_S_T\\c_bak.jpg");
//		FileOutputStream o = new FileOutputStream(f);
//		int c;
//		while((c=b.read())!=-1){
//			o.write(c);
//		}
		
//		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
//		String l = r.readLine();
//		OutputStreamWriter b = new OutputStreamWriter(System.out,"GBK");
//		b.write(l);
//		b.flush();
		
//		System.out.println(IOUtils.toString(new URL("http://rebecca.iteye.com/blog/234724")));
//		System.out.println(FilenameUtils.normalize("d:/sfd/32/fsd"));
//		System.out.println(FilenameUtils.getExtension("d:/sfd/32/fsd.s"));
		
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
//		ApplicationContext ac = new ClassPathXmlApplicationContext("/configure/applicationContext.xml");
//		TestDI td = ac.getBean("testDI", TestDI.class);
//		td.print();
//		TestAnotationDI td = ac.getBean("testAnotationDI", TestAnotationDI.class);
//		td.print();
		
		
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
