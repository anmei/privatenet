package com.rhcheng.news.webmagic.dg;

import java.util.Collection;
import java.util.Map;

import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.webmagic.NewsListProcessor;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class DgPipeLine implements Pipeline{
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		System.out.println("ok");
		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			System.out.println(entry.getKey());
	        NewsListProcessor.res.addAll((Collection<? extends NewsAbstract>) entry.getValue());
	    }
		
	}
	
	

}
