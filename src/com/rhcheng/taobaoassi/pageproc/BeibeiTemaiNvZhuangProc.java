package com.rhcheng.taobaoassi.pageproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.rhcheng.news.webmagic.BaseSpider;
import com.rhcheng.taobaoassi.ActionStart;
import com.rhcheng.taobaoassi.Constants;
import com.rhcheng.taobaoassi.MainStart;
import com.rhcheng.taobaoassi.model.ImgModel;
import com.rhcheng.taobaoassi.model.ProductInfo;
import com.rhcheng.taobaoassi.model.SellInfo;
import com.rhcheng.taobaoassi.model.TaobaoModel;
import com.rhcheng.util.digest.EncryptUitls;

import net.sf.json.JSONObject;
import us.codecraft.webmagic.Page;


// 根据专场URL获取该专场的商品
// 贝贝专场分页获取所有商品的方式  http://www.beibei.com/martshow/182112.html?sort=hot&cate=0&page=2

public class BeibeiTemaiNvZhuangProc extends BaseSpider{

	@Override
	public void detailProcess(Page page) throws RuntimeException {
		// 第二次才是真正处理商品详情
		if(super.thv.getF() == 2){
			// 获取商品详情页信息
			Document doc = page.getHtml().getDocument();
			if(doc!=null){
				String wed = doc.toString();
				TaobaoModel tbm = new TaobaoModel();
				Matcher imgmach = Constants.beibeitemaiimg.matcher(wed);
				Matcher skumach = Constants.beibeitemaisku.matcher(wed);
				Matcher titlemach = Constants.beibeitemaitile.matcher(wed);
				if(titlemach.find()){
					String title = titlemach.group();
					title = title.replaceAll("[';]", "").trim();
					tbm.setTitle(title);
				}
				
				if(skumach.find()){
					String sku = skumach.group();
					sku = EncryptUitls.encodingtoStr(sku.replaceAll("[';]", "").trim());
					System.out.println(sku);
					List b = BeibeiTemaiNvZhuangProc.getSellInfo(sku);
					System.out.println(b);
				}
				if(imgmach.find()){
					String imgs = imgmach.group();
					imgs = imgs.replaceAll("['\\[\\]\";]", "").trim();
					imgs = imgs.replaceAll("\\\\", "");
					System.out.println(imgs);
					List a = BeibeiTemaiNvZhuangProc.getImageInfo(imgs);
					System.out.println(a);
				}
	
				tbm.setSum(100);
			
				super.thv.getTaobaomodels().add(tbm);
			}
			
			
		}
		// 第一次抓取是抓取商品列表
		if(super.thv.getF() == 1){
			// 获取该专场所有商品URL 
			List<String> requesturl = new ArrayList<String>();
			Document doc = page.getHtml().getDocument();
			Elements elements = doc.select(".J_mart ul li");
			for(Element ele:elements){
				String url = ele.select("a").get(0).attr("abs:href");// 商品详情url
				String name = ele.select("a div").get(0).ownText();// 商品名
				requesturl = MainStart.judgeDupAndadd(name, url, getDBProducInfo(), requesturl);
				
			}
			super.addTargetUrls(requesturl, page);
			
		}
		// 读写数据 持久化
		if(super.isLast()){
			// 将csv中的相关信息写入数据库中,制作csv商品数据包
			MainStart.persisProduct(super.thv.getTaobaomodels());
		}
		
		
	}
	
	

	// 获取商品类别
	public int getCategory(){
		return 1;
	}
	// 获取数据库中对应商品类别的商品数据,如此页面处理器就获取女装相关的数据库数据
	public List<ProductInfo> getDBProducInfo(){
		return ActionStart.nvzhuang;
	}
	
	
	/* skumap格式：         
	"v7353": "M",
  	"v7354": "L",
  	"v7355": "XL",
  	"v468": "酒红色"*/
	// {"sku_id_map":{"3":[462,468],"161":[7353,7354,7355]},"sku_kv_map":{"k3":"颜色","v462":"白色","k161":"尺码","v7353":"M","v7354":"L","v7355":"XL","v468":"酒红色"},"sku_stock_map":{"v462":600,"v7353":398,"v462v7353":{"id":32630009,"stock":200,"price":17800,"origin_price":29900},"v7354":399,"v462v7354":{"id":32630010,"stock":200,"price":17800,"origin_price":29900},"v7355":400,"v462v7355":{"id":32630011,"stock":200,"price":17800,"origin_price":29900},"v468":597,"v468v7353":{"id":32630012,"stock":198,"price":17800,"origin_price":29900},"v468v7354":{"id":32630013,"stock":199,"price":17800,"origin_price":29900},"v468v7355":{"id":32630014,"stock":200,"price":17800,"origin_price":29900},"v0":1197},"vids":{"462":"462","7353":"7353","7354":"7354","7355":"7355","468":"468"}}
	public static Map<String,String> skumap = new HashMap<String,String>();
	/**
	 * 获取销售商品sku信息
	 * @param skudata
	 * @return
	 */
	public static List<SellInfo> getSellInfo(String skudata){
		JSONObject json = JSONObject.fromObject(skudata);
		
		// 获取sku map
		JSONObject data = json.getJSONObject("sku_kv_map");
		Iterator<String> keys = data.keys();
		while(keys.hasNext()){
			String k = keys.next();
			skumap.put(k, data.getString(k));
		}
		
		// 获取sku销售属性
		List<SellInfo> list = new ArrayList<SellInfo>();
		JSONObject skustock = json.getJSONObject("sku_stock_map");
		Iterator<String> stockkeys = skustock.keys();
		while(stockkeys.hasNext()){
			String sk = stockkeys.next();
			int secInd = sk.indexOf('v', 2);
			if(secInd != -1){// 过滤v字符存在2次以上的
				JSONObject substock = skustock.getJSONObject(sk);
				SellInfo sellinfo = new SellInfo();
				sellinfo.setPrice(substock.getInt("price"));
				sellinfo.setNumber(substock.getInt("stock"));
				sellinfo.setColor(skumap.get(sk.substring(0, secInd)));
				sellinfo.setSize(skumap.get(sk.substring(secInd)));
				list.add(sellinfo);
			}
			
		}
		
		return list;
	}
	
	
	/**
	 * 获取主图信息
	 * @param skudata
	 * @return
	 */
	// 468:http://b1.hucdn.com/upload/item/1603/01/12100879673728_800x800.jpg,462:http://b1.hucdn.com/upload/item/1603/01/12123616443728_800x800.jpg
	public static List<ImgModel> getImageInfo(String skudata){
		String[] array1 = skudata.split(",");
		List<ImgModel> res = new ArrayList<ImgModel>();
		if(array1.length>0){
			for(int i=0;i<array1.length;i++){
				if(StringUtils.isNotBlank(array1[i])){
					ImgModel im = new ImgModel();
					im.setColor(skumap.get("v"+array1[i].split(":http")[0]));
					im.setOriginurl("http"+array1[i].split(":http")[1]);
					im.setImgNamemd5("");
					res.add(im);
				}
			}
		}
		return res;
	}
	
	
	@Override
	public String imgRelativeToAbsolute(String url, String content) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


