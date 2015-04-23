package com.rhcheng.user.action;

import javax.annotation.Resource;
import org.apache.struts2.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rhcheng.baseJqgrid.JqGridSearch;
import com.rhcheng.baseJqgrid.ListResult;
import com.rhcheng.baseJqgrid.QGridListAction;
import com.rhcheng.user.service.UserService;

@Controller
@RequestMapping(value="/jqgrid/*")
public class JQgridListTestAction extends QGridListAction{
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value="toMainPage")
	public String toMainpage(){
		return "jsp_zadminAndJqgrid/main/main";
	}

	@RequestMapping(value="toUserPage")
	public String toUserPage(){
		return "jsp_zadminAndJqgrid/user/onepage";
	}

	
	@Override
	@ResponseBody
	@RequestMapping(value="userlist")
	public ListResult list() {
		System.out.println(super.getPage()+" "+super.getRows()+" "+super.getFilters());
		ListResult lr = new ListResult();
		try {
			lr.setErrorInf("成功");
			lr.initPage(userService.getStudentList(super.getPage(), super.getRows(),JqGridSearch.convert(super.getFilters())));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return lr;
	}
	
	
	
	
}
