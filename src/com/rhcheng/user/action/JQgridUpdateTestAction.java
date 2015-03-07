package com.rhcheng.user.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rhcheng.baseJqgrid.ListResult;
import com.rhcheng.baseJqgrid.QGridOperAction;
import com.rhcheng.user.entity.Student;
import com.rhcheng.user.service.UserService;

@Controller
@RequestMapping(value="/jqupdate/*")
public class JQgridUpdateTestAction extends QGridOperAction {
	
	private Student student;
	
	
//	一个控制器可以有任意数量的@ModelAttribute方法。所有这些方法都在@RequestMapping方法被调用之前调用。
//	它把请求参数（/update?student=text）加入到一个名为student的model属性中，在它执行后update被调用，
//	返回视图名helloWorld和model已由@ModelAttribute方法生产好了。
//	接收页面传过来的值，置于model模型中
	@ModelAttribute("student")// 模型中的属性值
	public void setStudent(Student student) {// 通过Student对象接收页面的传值
		this.student = student;
	}

	@Resource
	private UserService userService;


	@Override
	public ListResult add(HttpServletRequest request) {
		userService.update(student);
		super.getListResult().setErrorInf("增加出错误了。");
		return super.getListResult();
	}

	@Override
	@RequestMapping(value="update")
	@ResponseBody
	public ListResult update(HttpServletRequest request) {
		Integer studentId = this.getId(request).intValue();
		
		System.out.println("ok,i am update"+studentId);
//		System.out.println(1/0);
		userService.update(student);
		super.getListResult().setErrorInf("更新出错误了。");
		return super.getListResult();
	}

	@Override
	public ListResult delete(HttpServletRequest request) {
		Integer studentId = this.getId(request).intValue();
		System.out.println("ok,i am update"+studentId);
		userService.update(student);
		super.getListResult().setErrorInf("删除出错误了。");
		return super.getListResult();
	}
	
	
	
	

}
