package com.rhcheng.util.mail;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.rhcheng.util.LoadProperties;
/**
 * 邮件发送
 * @author RhCheng
 * @date   2014-5-23
 * @since  1.0
 */
@Component
public class SendMail{
	@Resource
	private JavaMailSenderImpl sender;
	
	private Executor executor = Executors.newFixedThreadPool(3);// 设置线程数
	
	/**
	 * 发送邮件的方法
	 * 
	 * @param subject
	 * 			邮件主题
	 * @param toEmail
	 *          接收者邮箱
	 * @param emailText
	 * 			邮件内容
	 * @since 1.0
	 */
	public void send(final String subject,final String toEmail,final String emailText) {
		Runnable task = new Runnable() {

			@Override
			public void run() {
				sender.setHost("smtp.163.com");
				sender.setUsername("crh15270989335@163.com");
				sender.setPassword("a220022z");
				// 获取JavaMailSender bean
				MimeMessage mailMessage = sender.createMimeMessage();
				// 设置utf-8或GBK编码，否则邮件会有乱码
				try {
					MimeMessageHelper messageHelper = new MimeMessageHelper(
							mailMessage, true, "utf-8");
					messageHelper.setTo(InternetAddress.parse(toEmail));// 接受者
					messageHelper.setFrom("crh15270989335@163.com");
					messageHelper.setSubject(subject);// 主题
					// 邮件内容，注意加参数true
					//设置文本的内容
					messageHelper.setText(emailText, true);
					sender.send(mailMessage);
				} catch (Exception e){ 
					e.printStackTrace();
				}

			}

		};
		// 使用Executor框架的线程池执行邮件发送任务
		executor.execute(task);
	}
	
	public static void main(String[] args){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("/configure/applicationContext.xml") ;  
		SendMail sendMail = (SendMail) beanFactory.getBean("sendMail");  
		
		sendMail.send("fds", "550414610@qq.com", LoadProperties.getPropertieByKey("active", "/properties/emailContent.properties"));
		
	}
	
}