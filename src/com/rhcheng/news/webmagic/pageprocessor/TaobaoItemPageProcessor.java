package com.rhcheng.news.webmagic.pageprocessor;

import java.util.ArrayList;
import java.util.List;

import com.rhcheng.news.action.CaptureAction;
import com.rhcheng.news.webmagic.BaseSpider;

import us.codecraft.webmagic.Page;
/**
 * 
 * @author anmei
 *
 */
public class TaobaoItemPageProcessor extends BaseSpider{

	@Override
	public void detailProcess(Page page) throws RuntimeException {
	    CaptureAction.res.add(page.getRawText());
	    // 继续添加新的待抓取url
	    List<String> requesturl = new ArrayList<String>();
	    requesturl.add("http://blog.csdn.net/hailangamy/article/details/7037128");
	    super.addTargetUrls(requesturl, page);
	}

	@Override
	public String imgRelativeToAbsolute(String url, String content) {
		// TODO Auto-generated method stub
		return null;
	}

}
