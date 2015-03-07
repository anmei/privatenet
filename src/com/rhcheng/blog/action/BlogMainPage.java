package com.rhcheng.blog.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/blogMainPage/*")
public class BlogMainPage {
	
	@RequestMapping(value="blogmainpage")
	public String toMainPage(){
		return "blog/blogmain";
	}
}
