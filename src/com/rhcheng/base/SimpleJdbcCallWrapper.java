package com.rhcheng.base;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Component;

/**
 * 
 * SpringJdbc 存储过程调用模板类
 * 
 * @author RhCheng
 * 
 */
@Component("simpleJdbcCallWrapper")
public class SimpleJdbcCallWrapper {

	private static Logger log = Logger.getLogger(SimpleJdbcCallWrapper.class);

	@Resource
	private SimpleJdbcCallFactory simpleJdbcCallFactory;

	/**
	 * 无返回值，存储过程调用
	 * 
	 * @author flatychen
	 * @param callableName
	 *            存储过程名称
	 * @param args
	 *            参数，不包含输出参数,顺序须一致
	 */
	public void call(String callableName, Object args[]) {
		this.logs("call", callableName, args);
		if (args == null) {
			args = new Object[0];
		}
//		this.simpleJdbcCallFactory.getObject().withProcedureName(callableName)
//				.execute(args);

	}
	

	/**
	 * 返回多个值的存储过程调用，Map 封装 ，可以包含list，Map key为存储过程输出变量名称
	 * 
	 * @author flatychen
	 * @param callableName
	 *            存储过程名称
	 * @param args
	 *            参数，不包含输出参数,顺序须一致
	 * @return 封装调用结果
	 */
	public Map<String, Object> callForMap(String callableName, Object args[]) {
//		this.logs("callForMap", callableName, args);
//		return this.simpleJdbcCallFactory.getObject()
//				.withProcedureName(callableName).execute(args);
	    return null;
	}

	/**
	 * 返回对应Class对象的List,需显示的指定[数据类型]与[参数名称]对应,只需输入参数<Br />
	 * 如: <code>
	 * 
	 * 	SqlParameter sqlParameters[] = {
				new SqlParameter("V_ID", Types.INTEGER)
				};
	 * 
	 * </code>
	 * 
	 * @author flatychen
	 * @param callableName
	 *            存储过程名称
	 * @param clazz
	 *            返回List对象的class
	 * @param args
	 *            参数，不包含输出参数,顺序须一致
	 * @param sqlInParameters
	 *            输入参数类型数组,
	 * @return
	 */
	public <T> List<T> callSpecifiedParasForList(String callableName,
			Class<T> clazz, Object args[], SqlParameter sqlInParameters[]) {
		this.logs("callSpecifiedParasForList", callableName, args);
//		return (List<T>) this.simpleJdbcCallFactory
//				.getObject()
//				.withProcedureName(callableName)
//				.withoutProcedureColumnMetaDataAccess()
//				.returningResultSet(callableName,
//						BeanPropertyRowMapper.newInstance(clazz))
//				.returningResultSet(callableName,ParameterizedBeanPropertyRowMapper.newInstance(clazz))
//				.declareParameters(sqlInParameters).execute(args)
//				.get(callableName);
		return null;
	}

	/**
	 * 返回对应Class对象的Bean,如大于一个,则只返回第一个,需显示的指定[数据类型]与[参数名称]对应,只需输入参数<Br />
	 * 如: <code>
	 * 
	 * 	SqlParameter sqlParameters[] = {
				new SqlParameter("V_ID", Types.INTEGER)
				};
	 * 
	 * </code>
	 * 
	 * @author flatychen
	 * @param callableName
	 *            存储过程名称
	 * @param clazz
	 *            返回List对象的class
	 * @param args
	 *            参数，不包含输出参数,顺序须一致
	 * @param sqlInParameters
	 *            输入参数类型数组,
	 * @return
	 */
	public <T> T callSpecifiedParasForBean(String callableName, Class<T> clazz,
			Object args[], SqlParameter sqlInParameters[]) {
		return this.callSpecifiedParasForList(callableName, clazz, args,
				sqlInParameters).get(0);
	}

	private void logs(String methodName, String callableName, Object args[]) {
		if (log.isInfoEnabled()) {
			log.info(MessageFormat.format(
					"======>>{5}'{' name:call {1}, args:{3} '}'", 1,
					callableName, 3, Arrays.toString(args), 5, methodName));
		}
	}

}
