package com.rhcheng.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/*******************************************************************************
 * 读取properties属性配置文件公共类
 * 
 * @author RhCheng
 * @version 2013-07-01 
 ******************************************************************************/
public class LoadProperties {
	// 通过静态变量 缓存起来
	private static Map<String, String> propertiesMap =null;
	
	private static Logger log = Logger.getLogger(LoadProperties.class);

	protected LoadProperties() {
	}

	/**
	 * 根据键取出对应的值
	 * 
	 * @param key
	 *            需要从fileName资源文件中取值的 key
	 * @param fileName
	 *            资源文件名称,class全路径。例如： /properties/emailContent.properties
	 * @author zengxiangtao
	 */
	public static String getPropertieByKey(String key, String fileName) {
		InputStream inputStream = null;
		try {
			inputStream = LoadProperties.class.getResourceAsStream(fileName);
			Properties properties = new Properties();
			properties.load(inputStream);
			String strValue = properties.getProperty(key);
			log.info("-------------->getPropertieByKey---->fileName:"+fileName+",Key:"+key+",Value:"+strValue);
			return strValue;
		} catch (IOException ie) {
			log.error("--------------->getPropertieByKey fileName:"+fileName+",Key:"+key+"---->Exception:"+ie.getMessage());
			ie.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error("--------------->Pro"+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 根据键取出对应的值
	 * 
	 * @param key
	 *            需要从fileName资源文件中取值的 key
	 * @param fileName
	 *            资源文件名称
	 * @author zengxiangtao
	 */
	public static String getPropertieByKeyFromCache(String key, String fileName) {
		StringBuffer sb = new StringBuffer(50);
		if(StringUtils.isNotBlank(fileName)){
			sb.append(fileName).append(".");
		}
		sb.append(key);
		String tempKey = sb.toString();
		// 从缓存的Map中取值
		if (null != propertiesMap && propertiesMap.containsKey(tempKey)) {
			log.info("--------------->getPropertieByKeyFromCache---->fileName:"+fileName+",Key:"+key);
			return  propertiesMap.get(tempKey);
		}
		String valueStr = getPropertieByKey(key, fileName);
		if(null == propertiesMap){
			propertiesMap = new HashMap<String, String>();
		}
		propertiesMap.put(tempKey, valueStr);
		return valueStr;
	}

	/*----------------------------------------------------------------------------------------------*/

	/**
	 * 默认资源文件里获得对应的值
	 * 
	 * @author zengxiangtao
	 */
//	public static String getProperty(String key) {
//		return getPropertieByKeyFromCache(key, SysConstants.PROPERTIESFILE);
//	}

	/**
	 * 清除缓存cache
	 * 
	 * @author zengxiangtao
	 */
	public static void clearPropertiesCache() {
		log.info("-----------------------clear properties cache.............................................");
		if(null != propertiesMap){
			propertiesMap.clear();
		}
	}

}
