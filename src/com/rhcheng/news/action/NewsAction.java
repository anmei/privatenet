package com.rhcheng.news.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rhcheng.common.PageFormBean;
import com.rhcheng.common.Pagination;
import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.service.IDgService;
/**
 * news action
 * @author RhCheng
 * @date   2014-12-9
 */
@Controller
@RequestMapping("/news/*")
public class NewsAction {
	@Resource
	private IDgService dgService;
	
	/**news list*/
	@RequestMapping(value="list")
	public String newsList(PageFormBean pf,String tableName,Model model){
		
//		new CarViolationResult().query2("fs", "fds", "fsd");
		
		
		if(StringUtils.isBlank(tableName)){tableName = "dgnewsabs";}
		Pagination<NewsAbstract> newsabs = dgService.findNewsAbs(pf,tableName);
		model.addAttribute("newsabs", newsabs);
		model.addAttribute("tableName", tableName);
		return "news/list";
	}
	
	@RequestMapping(value="getMoreList")
	public String getMoreList(PageFormBean pf,String tableName,Model model){
		if(StringUtils.isBlank(tableName)){tableName = "dgnewsabs";}
		Pagination<NewsAbstract> newsabs = dgService.findNewsAbs(pf,tableName);
		model.addAttribute("newsabs", newsabs);
		return "news/morenewslist";
	}
	
	
	

	//---------------------------------------------------------------------news detail
	
	@RequestMapping(value="detail")
	public String newsDetail(String url,String absid,String tableName,Model model){
		model.addAttribute("url", url);
		model.addAttribute("absid", absid);
		model.addAttribute("tableName", tableName);
		return "news/detail";
	}
	
	/**
	 * 
	 * @param url
	 * @param tableName abstract table name
	 * @return
	 */
	@RequestMapping(value="getContent.json")
	public ModelAndView getContent(String url,String absid,String tableName){
		ModelAndView mv = new ModelAndView();
		NewsAbstract newabs = dgService.getNewsabsByabsid(absid, tableName);
		tableName = tableName.replace("abs", "detail");
		String content = dgService.getNewsDetailByAbsid(absid, tableName).getContent();
		content = content==null?"暂无内容":content;
		mv.addObject("content", content);
		mv.addObject("newsabs", newabs);
		return mv;
	}
	
	
	
}
