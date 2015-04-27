package com.rhcheng.user.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rhcheng.common.PageFormBean;
import com.rhcheng.common.Pagination;
import com.rhcheng.user.entity.User;
import com.rhcheng.user.service.TestService;


@Controller
//如下注解表示非单例，对每个线程都各自创建对应的Controller实例, "singleton" default
//@Scope("prototype")//http://blog.csdn.net/mastermind/article/details/1932787;
@RequestMapping(value="/test/*")
public class TestAction {
	@Resource
	private TestService testService;
	
	private long id = 0L;

	
	/**
	 * 未登陆时对ajax的拦截
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@RequestMapping(value="noLogin")
	// ModelAndView返回的数据根据不同的解析会返回不同的数据格式，json、html等
	// ModelAndView可以满足所有的返回情况
	public ModelAndView noLogin(@ModelAttribute("name") String name,@ModelAttribute("userid") Long userid,Model model){
		ModelAndView mv = new ModelAndView();
		mv.addObject("res", id++);   
		System.out.println(id+" "+name+" "+userid);
		
		
		// 重定向
		//mv.setView(new RedirectView("jsontest"));
		//mv.setViewName("redirect:jsontest");
		
		// 非重定向访问另一个action
		//mv.setViewName("forward:jsontest");
		
		// 直接返回页面
		//mv.setViewName("user/nologin");
		
		return mv;
	}
	
	@RequestMapping(value="jsontest")
	@ResponseBody
	// responsebody可以直接返回实体
	public User returnJson(){
		System.out.println("fsd");
		User user = new User();
		user.setName("hah虎");
		user.setAddress("fds");
		user.setUserid(1L);
		user.setBirthday(new Date());
		return user;
		
	}
	
	
	
	
	
	/**
	 * 未登陆时对普通请求的拦截
	 * @return
	 */
	@RequestMapping(value="noLoginCommon")
	public String noLoginCommon(){
		// 直接返回页面
		//return "user/nologin";
		
		// 重定向到另一个action
		//return "redirect:noLogin.json";
		
		// 非重定向到另一个aciton
		return "forward:noLogin.json";
		
		
		
	}
	
	
	@RequestMapping(value="testone.action")
	public ModelAndView testone(){
		System.out.println("fds");
		ModelAndView mv = new ModelAndView();
		Map<String,String> data = new HashMap<String,String>();
		data.put("one", "one");
		data.put("two", "two");
		mv.addObject("res", "true");
		mv.addObject("haha", "你哈");
		mv.addObject("you", data);
		mv.setViewName("user/login");
		return mv;
	}
	
	/**
	 * 分页测试
	 * @param page
	 * @return
	 */
	@RequestMapping(value="paginationTest.action")
	public String PaginationTest(PageFormBean page,Model model){
		System.out.println(page.getPageSize()+" "+page.getToPage());
		
		model.addAttribute("pagetest", new Pagination<User>());
		return "user/login";
	}
	
	
	@RequestMapping(value="testEhcache.action")
	public String testEhcache(String param){
		String res = testService.find(param);
		System.out.println(res);
		return "";
	}
	
	
	
}
