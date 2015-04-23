package com.rhcheng.util;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;


/*******************************************************************************
 * 系统服务门面类
 * 
 * <pre>
 * 系统非注入service由此定义被调用
 * </pre>
 * 
 * @author zengxiangtao
 * @version 2013-07-01
 ******************************************************************************/
public class ServiceFacade {
	/**
	 * 从spring容器里获bean
	 * 
	 * @author zengxiangtao
	 * @param <T>
	 * */
	public static <T> T getBean(String beanName, Class<T> requiredType) {
		
		return ContextLoader.getCurrentWebApplicationContext().getBean(
				beanName, requiredType);
		
	}
	
	
	/*public static UninjectService getUnjectService(){
		return getBean("uninjectService", UninjectService.class);
	}*/
	
	
	/**
	 * 获取spring bean 通用的方法
	 */
	
	/*BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml") ;  
	IMessageSendOfWebservice messageSendOfWebservice = (IMessageSendOfWebservice) beanFactory.getBean("messageSendOfWebservice");  
	*/
	
	

	/**
	 * 东邮网收银台服务
	 * 
	 * @author zengxiangtao
	 * @param <T>
	 * */
	/*public static DgPayService getDgPayService() {
		return getBean("dgPayServiceImpl", DgPayService.class);
	}*/

	/**
	 * 会员服务
	 * 
	 * @author zengxiangtao
	 * @param <T>
	 * */
	/*public static MemberService getMemberService() {
		return getBean("memberServiceImpl", MemberService.class);
	}*/

	/**
	 * 短信接口
	 * 
	 * @author zengxiangtao
	 * @param <T>
	 * */
	/*public static SmsService getSmsService() {
		return getBean("smsServiceImpl", SmsService.class);
	}*/

	/**
	 * 会员服务
	 * 
	 * @author zengxiangtao
	 * @param <T>
	 * */
	/*public static MemberDAO getMemberDAO() {
		return getBean("memberDAOImpl", MemberDAO.class);
	}*/
	
}
