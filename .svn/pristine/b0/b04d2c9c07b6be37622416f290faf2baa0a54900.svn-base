package com.rhcheng.util.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.json.JSONUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.rhcheng.user.entity.User;
import com.rhcheng.util.date.DateUtils;

/*******************************************************************************
 * Json工具类
 * @author RhCheng
 ******************************************************************************/
public class JsonUtils {

	private static final Logger log = Logger.getLogger(JsonUtils.class);
	
	/**
	 * json response
	 * 可以直接输出Object对象
	 * @param response
	 * @param obj
	 * 			Object
	 * @param jsonOutType
	 * 			whether return json type
	 * @param ignoreHierarchy
	 */
	public static void jsonOut(HttpServletResponse response,Object obj, boolean jsonOutType,boolean ignoreHierarchy) {
		try {
			String json = JSONUtil.serialize(obj, null, null, ignoreHierarchy,false);
			//HttpServletResponse response = this.getResponse();
			if (!jsonOutType) {
				response.setContentType("text/html;charset=utf-8");
			} else {
				response.setContentType("application/json;charset=UTF-8");
			}
			int length = json.getBytes("utf-8").length;
			response.setContentLength(length);
			outPrint(response,json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * json response 输出字符串类型
	 * @param response
	 * @param msg
	 * 			String parameter
	 */
	public static void outPrint(HttpServletResponse response,String msg) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(msg);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	
	/**
	 * 对象（一般是犹如map类型的有key与value的对象）转成JSON字符串
	 * 
	 * @param bean
	 * @param 日期格式化
	 * @author RhCheng
	 **/
	public static String beanToJsonStr(Object bean, final String dateFormat) {
		JSONObject json = null;
		if (StringUtils.isNotBlank(dateFormat)) {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonValueProcessor() {
						public Object processObjectValue(String key,
								Object value, JsonConfig arg2) {
							if (value == null) {
								return "";
							} else {
								return DateUtils.formatDate((Date) value,
										dateFormat);
							}
						}

						public Object processArrayValue(Object arg0,
								JsonConfig arg1) {
							return null;
						}
					});
			jsonConfig.registerJsonValueProcessor(Timestamp.class,
					new JsonValueProcessor() {
						public Object processObjectValue(String key,
								Object value, JsonConfig arg2) {
							if (value == null) {
								return "";
							} else {
								return DateUtils.formatDate((Date) value,
										dateFormat);
							}
						}

						public Object processArrayValue(Object arg0,
								JsonConfig arg1) {
							return null;
						}
					});
			json = JSONObject.fromObject(bean, jsonConfig);
		} else {
			json = JSONObject.fromObject(bean);
		}
		String rs = json.toString();
		log.info("---------------------beanToJsonStr---->rs:" + rs);
		return rs;
	}

	
	/**
	 * 将实体VO Object 或者 JavaBean换成Json字符串
	 * @param vo
	 * @return
	 */
	public static String vo2JsonString(Object vo){
        JSONObject json = JSONObject.fromObject(vo); 
		return  json.toString();
	}
	
	
	
	/**
	 * 将业务数据map转换成Json字符串
	 * @return json串
	 */
	public static String map2JsonString(Map map){
		map.put("success", "true");
        JSONObject json = JSONObject.fromObject(map); 
		return  json.toString();
	}
	
	/**
	 * 将业务数据List转换成Json字符串
	 * @return json串 如： {"rows":[{},{}],"totalCount":10}
	 */
	public static String voList2JsonString(List list){
		
		Map testmap = new HashMap();
		testmap.put("rows", (Object)list);
		testmap.put("totalCount", (Object)new Integer(list.size()));
		JSONObject json = JSONObject.fromObject(testmap); 
		return  json.toString();
	}
	
	/**
	 * 分页的情况下将业务数据List转换成Json字符串
	 * 
	 * @param list 业务数据List
	 * @param totalCount 总条数
	 * @return json串 如： {"rows":[{},{}],"totalCount":10}
	 */
	public static String voList2JsonString(List list, int totalCount){
        
        Map testmap = new HashMap();
        testmap.put("rows", (Object)list);
        testmap.put("totalCount", (Object)new Integer(totalCount));
        JSONObject json = JSONObject.fromObject(testmap); 
		return  json.toString();
	}
	
	/**
	 * 将业务数据List转换成Json字符串，其中VO必须实现JsonVO接口，并实现其toJsonString方法
	 * 手动完成转换
	 * @return json串 如： {"rows":[{},{}],"totalCount":10}
	 */
	public static String customVOList2JsonString(List list){
		StringBuffer jsonBuffer = new StringBuffer();
		jsonBuffer.append("{");
		jsonBuffer.append("'totalCount':'").append(list.size()+"',");
		//拼其中rows的内容
		jsonBuffer.append("'rows':[");
		Iterator iter = list.iterator();
		while(iter.hasNext()){
			JsonVO vo = (JsonVO)iter.next();
			jsonBuffer.append(vo.toJsonString());
			if(iter.hasNext()){
				jsonBuffer.append(",");
			}
		}
		jsonBuffer.append("]");
		
		jsonBuffer.append("}");
		return  jsonBuffer.toString();
	}
	
	
	
	/**
	 * 把JsonStr转换成bean
	 * 
	 * @author RhCheng
	 * **/
	public static <T> T jsonStrToBean(String jsonStr, Class<T> beanClass,
			String dateFormat) {
		if (StringUtils.isBlank(jsonStr)) {
			log.info("------------jsonStrToBean------ json str is empty");
			return null;
		}
		JSONObject json = JSONObject.fromObject(jsonStr);
		if (StringUtils.isNotBlank(dateFormat)) {
			String[] dateFormats = new String[] { dateFormat };
			JSONUtils.getMorpherRegistry().registerMorpher(
					new DateMorpher(dateFormats));
		}
		return (T) json.toBean(json, beanClass);
	}

	/**
	 * 把JsonStr转换成Map
	 * 
	 * @author RhCheng
	 * **/
	public static Map<String, String> jsonStrToMap(String jsonStr) {
		if (StringUtils.isBlank(jsonStr)) {
			log.info("------------jsonStrToMap------ json str is empty");
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

	
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			// if(v instanceof JSONArray){
			// List<Map<String, Object>> list = new
			// ArrayList<Map<String,Object>>();
			// Iterator<JSONObject> it = ((JSONArray)v).iterator();
			// while(it.hasNext()){
			// JSONObject json2 = it.next();
			// list.add(parseJSON2Map(json2.toString()));
			// }
			// map.put(k.toString(), list);
			// } else {
			map.put(k.toString(), v);
			// }
		}
		return map;
	}
	
	
	/**
	 * jsstr to list
	 * @param jsonStr
	 * @param cls
	 * @return
	 */
	public static List<?> jsonStrToList(String jsonStr, Class cls) {
		if (jsonStr == null || jsonStr.equals("") || jsonStr.equals("[]"))
			return null;

		jsonStr = jsonStr.substring(jsonStr.indexOf("["));
		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		return JSONArray.toList(jsonArray, cls);
	}
	
	
	public static List<?> jsonStrToList(String jsonStr, Class cls,final String dateFormat) {
		if (jsonStr == null || jsonStr.equals("") || jsonStr.equals("[]"))
			return null;

		JSONArray jsonArray = JSONArray.fromObject(jsonStr);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonValueProcessor() {
					public Object processObjectValue(String key,
							Object value, JsonConfig arg2) {
						if (value == null) {
							return "";
						} else {
							return DateUtils.formatDate((Date) value,
									dateFormat);
						}
					}

					public Object processArrayValue(Object arg0,
							JsonConfig arg1) {
						return null;
					}
				});
		try {
			return JSONArray.toList(jsonArray, cls.newInstance(), jsonConfig);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	

	/**
	 * 将json串转换成指定类型对象组成的list
	 *
	 * @param jsonString 格式如："[{name:\"zhangsan\",age:\"28\"},{name:\"lisi\",age:\"27\"}]"
	 * @return 指定类型对象组成的list
	 * added by kexianneng
	 */
	public static List JsonString2List(String jsonString,Class beanClass){
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for(int i=0;i<jsonArray.size();i++){
			JSONObject object = (JSONObject)jsonArray.get(i);
			Object bean = JSONObject.toBean(object,beanClass);
			list.add(bean);
		}	
		return list;
	}
	
	
	
	
	
	/**
	 * 返回数据更新成功信息
	 *
	 * @return 
	 */
	public static String success(){
		return "{success:true}";
	}
	
	/**
	 * 返回数据更新成功信息
	 *
	 * @return 
	 */
	public static String success(Object vo){
		String json = vo2JsonString(vo);
		return "{success:true," + json.substring(1, json.length() -1 ) + "}";
	}
	/**
	 * 返回数据更新失败信息
	 *
	 * @return 
	 */
	public static String failure(String errorMessage){
		return "{failure:true,description:'"+errorMessage+"'}";
	}
	

	
	
	
	
////////////////////////////////
//	jackson lib test
////////////////////////////////
//
//	User user=new User(); //Java Object
//	user.setName("hah虎");
//	user.setAddress("fds");
//	user.setUserid(1L);
//	user.setBirthday(new Date());
//	
//	Map<String,Object> m = new HashMap<String,Object>();
//	m.put("a", "abc");
//	m.put("b", user);
//	
//	List<Object> li = new ArrayList<Object>();
//	li.add(m);
//	li.add(user);
//	li.add("fsd");
//
//	ObjectMapper mapper = new ObjectMapper();
//	/** 时间格式等配置*/
//	mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
//	mapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));  
//	
//	String jsonstr = mapper.writeValueAsString(li); //返回字符串
//	System.out.println(jsonstr);
//	
//	List ma = mapper.readValue(jsonstr, ArrayList.class);
//	
//	Map a = (Map)ma.get(0);
//	/** POJO也转为map基本类型了*/
//	Map b = (Map)a.get("b");
//	System.out.println(b.get("birthday")+" "+b.get("name"));
//	
//	
	
	
	
	
}
