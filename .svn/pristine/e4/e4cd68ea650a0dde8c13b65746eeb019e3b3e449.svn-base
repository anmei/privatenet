package com.rhcheng.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.rhcheng.common.SysConstants;
import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.extract.TextExtract;
import com.rhcheng.news.service.IDgService;
import com.rhcheng.util.UtilClient;
import com.rhcheng.util.LoadProperties;
import com.rhcheng.util.string.NewsUtils;

/**
 * toutiao entertainment
 * @author RhCheng
 * @date   2014-9-18
 */
public class TouTiao {
	private String max_behot_time=null;
	private String max_create_time=null;
	
	private String getUrl(){
		String url = LoadProperties.getPropertieByKeyFromCache("toutiao_entertainment", SysConstants.NEWS_PROPERTIES);
		return url;
	} 
	
	/**瀑布式翻页*/
	public List<NewsAbstract> get300NewList(){
		int offset = 0;
		String suffix;
		String hereurl = "";
		List<NewsAbstract> list = new ArrayList<NewsAbstract>();
		List<NewsAbstract> tem = new ArrayList<NewsAbstract>();
		for(int i=0;i<30;i++){
			//hereurl = getUrl().replace("offset=0", "offset="+offset);
//			if(i==0){
//				hereurl = getUrl();
//			}
//			else if(i>0){
//				hereurl = getUrl() + "&max_behot_time="+tem.get(tem.size()-1).getCreate_time();
//			}
			suffix=(StringUtils.isBlank(max_behot_time)?"":"&max_behot_time="+this.max_behot_time)+(StringUtils.isBlank(max_create_time)?"":"&max_create_time="+this.max_create_time);
			hereurl = getUrl()+suffix;
			System.out.println(hereurl);
			tem = getNewsAbsList(hereurl);
			list.addAll(tem);
			offset += 20;
		}
		return list;
		
	}
	
	
	public List<NewsAbstract> getNewsAbsList(String url){
		String res = UtilClient.sendPostRequest(url, null, "UTF-8");
		JSONObject json = JSONObject.fromObject(res);
		JSONArray data = json.getJSONArray("data");
		List<NewsAbstract> list = new ArrayList<NewsAbstract>();
		
		for(int i=0;i<data.size();i++){
			NewsAbstract abs = new NewsAbstract();
			JSONObject jo = (JSONObject)data.get(i);
			abs.setTitle(jo.getString("title"));
			abs.setDate(NewsUtils.parseDate(jo.getString("datetime")));
			abs.setOriginalDate(jo.getString("datetime"));
			abs.setAuth(jo.getString("source"));
			abs.setUrl(jo.getString("display_url"));
			abs.setImgPath(getDisplayImgPath(jo));
			list.add(abs);
			if(i==data.size()-1){
				this.max_create_time=jo.getString("create_time");
			}
		}
		this.max_behot_time = json.getJSONObject("next").getString("max_behot_time");
		return list;
		
	}
	
	/**
	 * return all the display img
	 * @author RhCheng
	 * @date 2014-9-19
	 * @param jo
	 * @return
	 */
	public String getDisplayImgPath(JSONObject jo){
		String res = "";
		JSONArray iml = jo.getJSONArray("image_list");
		for(int i=0;i<iml.size();i++){
			JSONObject tem = (JSONObject)iml.get(i);
			res += tem.getString("url")+"|";
		}
		return res;
	}
	
	
	/**
	 * this return the backup img,all the img is the same 
	 * return max size is 2
	 * @author RhCheng
	 * @date 2014-9-19
	 * @param jo
	 * @return
	 */
	public String getMiddleImgPath(JSONObject jo){
		String res = "";
		Object mid = jo.get("middle_image");
		if(mid instanceof String){
			res = (String)mid+"|";
		}else if(mid instanceof JSONObject){
			JSONObject jobj = (JSONObject)mid;
			JSONArray iml = jobj.getJSONArray("url_list");
			if(iml != null){
				for(int i=0;i<=2&&i<iml.size();i++){
					JSONObject tem = (JSONObject)iml.get(i);
					res += tem.getString("url")+"|";
				}
			}
		}
		return res;
	}
	
	/**
	 * make all the img src in content is absolute
	 * @author RhCheng
	 * @date 2014-9-19
	 * @param content 
	 * @param imgPath absolute imgPath
	 * @return
	 */
	public static String makeImgAbsolute(String content,List<String> imgPath){
		List<String> imglist = TextExtract.getImgTag(content);
		for(int i=0;i<imgPath.size()&&i<imglist.size();i++){
			if(StringUtils.isNotBlank(imgPath.get(i)) && TextExtract.getImgSrc(imglist.get(i)).indexOf("http://") == -1){
				content = content.replace(TextExtract.getSrcValue(imglist.get(i)),imgPath.get(i));
			}
		}
		return content;
	}
	
	
	
	
	public static void main(String[] args) throws IOException{
//		String res = UtilClient.sendPostRequest(new TouTiao().getUrl(), null, "UTF-8");
//		List<NewsAbstract> li = new TouTiao().get300NewList();
//		for(NewsAbstract l:li){
//			System.out.println(l.getTitle());
//		}
		
		
		/**更新*/
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("/configure/applicationContext.xml") ;  
		IDgService dgService = (IDgService) beanFactory.getBean("dgService");
		TouTiao ex = new TouTiao();
		List<NewsAbstract> li = ex.get300NewList();
		dgService.updateNewsOfYLtoutiao(li,"ylnewsabs_id","ylnewsabs","ylnewsdetail");
		System.out.println("yl ok");
		
		
	}
	
}
