package com.rhcheng.user.action;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.rhcheng.user.formbean.UserFormBean;

@Controller
@RequestMapping("/user/*")
public class UserAction {
	
	/**
	 * 基本rest风格，同一资源可以根据请求后缀不同返回不同的数据格式
	 * .json/.action
	 * 返回json必须返回ModelAndView类型
	 * @return
	 */
	@RequestMapping(value="login")
	public ModelAndView login(){
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
	 * 通过string直接返回一个页面或重定向到另一个控制器
	 * @param model
	 * @return
	 */
	@RequestMapping(value="testReturnString.action")
	public String testReturnString(UserFormBean user, Model model){
		
		System.out.println(user.getName()+" "+user.getSex()+" "+user.getAge()+" "+user.getAddress());
		
		model.addAttribute("rko", "nihao哈哈");
		return "user/login";
		//return "redirect:login.action";
		//return "forward:login.json";
		
	}
	
	
	
	
	
}
