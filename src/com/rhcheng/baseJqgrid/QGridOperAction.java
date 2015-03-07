package com.rhcheng.baseJqgrid;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 基本增、删、改操作抽象基类
 * @author RhCheng
 * @date   2014-7-18
 */
public abstract class QGridOperAction {

	@RequestMapping(value="add")
	@ResponseBody
	public abstract ListResult add(HttpServletRequest request);
	
	@RequestMapping(value="update")
	@ResponseBody
	public abstract ListResult update(HttpServletRequest request);

	@RequestMapping(value="delete")
	@ResponseBody
	public abstract ListResult delete(HttpServletRequest request);
	
	
	/** 获取页面数据行对应的数据id*/
	public Long getId(HttpServletRequest request) {
		try {
			return Long.parseLong(request.getParameter("id"));
		} catch (Exception e) {
		}
		return null;
	}

	
	@RequestMapping(value="operate")
	public String operate(HttpServletRequest request){
		String oper = request.getParameter("oper");
		if (oper.equals("add")) {
			return "forward:add";
		} else if (oper.equals("edit")) {
			return "forward:update";
		} else if (oper.equals("del")) {
			return "forward:delete";
		}
		return "";
		
	}
	
	// 返回信息
	private ListResult listResult;
	
	
	public ListResult getListResult() {
		if(null == listResult){listResult = new ListResult();}
		return listResult;
	}

	public void setListResult(ListResult listResult) {
		this.listResult = listResult;
	}


	//异常信息
	protected String errorInf;

	public String getErrorInf() {
		return errorInf;
	}
	public void setErrorInf(String errorInf) {
		this.errorInf = errorInf;
	}
	
}