package com.rhcheng.test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * IOC、DI 容器初始化、bean的初始化、依赖注入bean
 * 配置注入需要set方法、构造方法等，注解注入不需要set方法、构造方法等
 * 
 * java代码面向接口编程，真正的实现由配置文件指定，低耦合、可重用
 * bean可以延迟初始化
 * Spring提供“singleton”和“prototype”两种基本作用域，另外提供“request”、“session”、“global session”三种web作用域；Spring还允许用户定制自己的作用域。
 * 注解实现Bean配置主要用来进行如依赖注入、生命周期回调方法定义等，不能消除XML文件中的Bean元数据定义，且基于XML配置中的依赖注入的数据将覆盖基于注解配置中的依赖注入的数据。
 * 基于@Autowired的自动装配，默认是根据类型注入，可以用于构造器、字段、方法注入。@Qualifier限定描述符除了能根据名字进行注入，还能进行更细粒度的控制如何选择候选者
 * Spring提供通过扫描类路径中的特殊注解类来自动注册Bean定义。同注解驱动事务一样需要开启自动扫描并注册Bean定义支持
 * @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了
 * 
 * 1、不需要任何注解，通过配置xml文件，通过set方法\构造方法等 DI（dependency inject）
 * 2、自动装配，不需要任何注解，在配置文件的bean中配置{@code autowire="byType"},减少配置文件的配置项，最终同样还是通过set方法\构造方法等 DI（dependency inject）
 * 3、@Autowired 基于注解注入，在需要的地方（字段、set方法、构造方法）上加此注解即可，同时需要在配置文件中配置相关的具体实现类
 * 4、基于注解(@comment\@service等)实现bean的定义初始化，无需再在配置文件中配置bean的实现
 * 
 * 
 * @author RhChen
 * @date   2015-5-6
 */
public class TestDI {
	private String a;
	private ISomeClass isc;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
	
	public ISomeClass getIsc() {
		return isc;
	}

	public void setIsc(ISomeClass isc) {
		this.isc = isc;
	}

	public void print(){
		System.out.println(a);
		isc.methodOne();
	}
	
	
}
