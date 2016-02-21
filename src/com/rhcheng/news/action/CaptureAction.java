package com.rhcheng.news.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/capture/*")
public class CaptureAction {
	
	@RequestMapping(value="captureTaoBaoItem")
	public ModelAndView captureTaoBaoItem(){
		return null;
	}
	
}
