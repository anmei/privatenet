package com.rhcheng.news;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.rhcheng.news.extract.SimpleSpider;
import com.rhcheng.taobaoassi.Constants;
import com.rhcheng.taobaoassi.MainStart;
import com.rhcheng.taobaoassi.model.TaobaoModel;
import com.rhcheng.taobaoassi.pageproc.BeibeiTemaiNvZhuangProc;
import com.rhcheng.util.digest.EncryptUitls;

import net.sf.json.JSONObject;

public class JsoupTest extends SimpleSpider<Object>{

	public static void main(String[] args) throws IOException {
//		String skudata = "[\"468:http://b1.hucdn.com/upload/item/1603/01/12100879673728_800x800.jpg","462:http:\/\/b1.hucdn.com\/upload\/item\/1603\/01\/12123616443728_800x800.jpg"]";
//		skudata = skudata.replaceAll("[\\[\\]\"", "");
//		System.out.println(skudata);
		
		JsoupTest js = new JsoupTest();
		js.setUrl("http://www.beibei.com/detail/180797-1189816.html");
		Document doc = null;
		doc = js.getDocument();
//		System.out.println(doc.toString());
		Elements sellinfo = null;
//		sellinfo = doc.select("div[class=detail-meta-bg clearfix]");
//		System.out.println(sellinfo.get(0).toString());
//		if(sellinfo != null && sellinfo.size()>0){
//			TaobaoModel tbm = new TaobaoModel();
//			// 获取商品相关信息
//			tbm.setTitle(MainStart.nameFilter(sellinfo.get(0).select("div[class=main-wrapper] div[class=title] h3").get(0).ownText()));
//			System.out.println(doc.select("div[class=main-wrapper] div[class=price-info view-SkuPriceInfo] span").get(0));
//			tbm.setPrice(Float.valueOf(doc.select("div[class=main-wrapper] div[class=price-info view-SkuPriceInfo] span").get(0).select("em").get(1).ownText()));
//			tbm.setSum(100);
//			System.out.println(tbm.getTitle()+" "+tbm.getPrice());
//			
//		}
		
		
		String wed = doc.toString();
		Matcher imgmach = Constants.beibeitemaiimg.matcher(wed);
		Matcher skumach = Constants.beibeitemaisku.matcher(wed);
		Matcher titlemach = Constants.beibeitemaitile.matcher(wed);
		if(titlemach.find()){
			String title = titlemach.group();
			title = title.replaceAll("[';]", "").trim();
			System.out.println(title);
		}
		List b = null;
		if(skumach.find()){
			String sku = skumach.group();
			sku = EncryptUitls.encodingtoStr(sku.replaceAll("[';]", "").trim());
			System.out.println(sku);
			b = BeibeiTemaiNvZhuangProc.getSellInfo(sku);
			System.out.println(b);
		}
		List a = null;
		if(imgmach.find()){
			String imgs = imgmach.group();
			imgs = imgs.replaceAll("['\\[\\]\";]", "").trim();
			imgs = imgs.replaceAll("\\\\", "");
			System.out.println(imgs);
			a = BeibeiTemaiNvZhuangProc.getImageInfo(imgs);
			System.out.println(a);
		}
		
		TaobaoModel tm = new TaobaoModel();
		tm = MainStart.makeTaobaoSellProp(tm, b, a);
		System.out.println(tm);
		
		
		
		
	}
	
	@Override
	public List<Object> findNewsList(String url) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
