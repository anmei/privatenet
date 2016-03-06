package com.rhcheng.taobaoassi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.rhcheng.news.service.ICrawlService;
import com.rhcheng.news.webmagic.CrawlModel;
import com.rhcheng.taobaoassi.model.ImgModel;
import com.rhcheng.taobaoassi.model.ProductInfo;
import com.rhcheng.taobaoassi.model.SellInfo;
import com.rhcheng.taobaoassi.model.TaobaoModel;
import com.rhcheng.taobaoassi.pageproc.BeibeiTemaiNvZhuangProc;
import com.rhcheng.util.ServiceFacade;

/**
 * 1、不更新商品价格数量，直到卖完就下架
 * 2、当某天上架的所有商品都下架了，就删除这一天有关商品的所有图片
 * 3、每天晚上抓取有关商品并上架，制作数据包时会排除已上架的相同的商品，如果相关商品已经售完，则做标记方便做下架处理
 * 4、每天晚上处理发货、退货、上架、下架
 * 
 * 商品唯一标识：商品详情源地址url
 * 
 * 
 * @author anmei
 *
 */
public class MainStart {
	public static List<TaobaoModel> taobaomodelList = new ArrayList<TaobaoModel>();
	
	public static void main(String[] args) {
		// 商品抓取url入口
		Map<String,CrawlModel> crawlModels = new HashMap<String,CrawlModel>();
		crawlModels.put("http://www.beibei.com/martshow/180797.html",
				getBeibeiTemaiCrawlModel("http://www.beibei.com/martshow/180797.html"));
		crawlModels.put("https://upload.taobao.com/auction/publish/publish.htm",
				getBeibeiTemaiCrawlModel("https://upload.taobao.com/auction/publish/publish.htm"));
		
		
		

		
		
		
		
	}
	
	
	//----------------------------------------------------------common utils
	
	// 配置抓取需要的元数据
	public static CrawlModel getBeibeiTemaiCrawlModel(String url){
		CrawlModel cm = new CrawlModel();
		cm.setCharset("UTF-8");
		cm.setMethod("GET");
		cm.setPageProcessor(BeibeiTemaiNvZhuangProc.class);
		cm.setUrl(url);
		return cm;
	}
	
	public static String nameFilter(String originName){
		return originName;
	}
	
	// 添加商品信息到数据库
	public static int persisProduct(List<TaobaoModel> tbm){
		if(tbm != null && tbm.size()>0){
			ICrawlService is = ServiceFacade.getBean("crawlService", ICrawlService.class);
			ProductInfo cm = new ProductInfo();
			for(TaobaoModel tb:tbm){
				// 添加到数据库
				cm.setCategory(tb.getCategory());
				cm.setGmtcreate(tb.getGmtcreate());
				cm.setName(tb.getName());
				cm.setNum(tb.getNum());
				cm.setOriginname(tb.getOriginname());
				cm.setUrl(tb.getUrl());
				is.addProductInfo(cm);
				
				//制作csv数据包
			}
			
			
			return tbm.size();
		}
		return 0;
	}
	
	// 与数据库中数据对比 是否已存在,url或者商品名称有一个相同即表示存在
	public static List<String> judgeDupAndadd(String name,String url,List<ProductInfo> dbproductinfo,List<String> requesturl){
		boolean issame = false;
		if(dbproductinfo!=null && dbproductinfo.size()>0){
			for(ProductInfo pi:dbproductinfo){
				if(pi.getOriginname().trim().equalsIgnoreCase(name.trim()) 
						|| pi.getUrl().equalsIgnoreCase(url)){
					issame = true;break;
				}
			}
		}
		if(!issame){
			requesturl.add(url);
		}
		return requesturl;
	}
	
	// 制作淘宝商品销售属性、主图片、颜色图片
	public static TaobaoModel makeTaobaoSellProp(TaobaoModel tbm,List<SellInfo> sellinfo,List<ImgModel> imgs){
		// 格式： "白色":"1213321:-312","红色"："312321:212","XL":"2423:21"
		Map<String,String> propmap = new HashMap<String,String>();
		if(sellinfo != null && sellinfo.size() > 0){
	//		设置自定义销售属性
	//		1627207:-1002:红颜色;1627207:-1003:蓝颜色;20509:-1001:170;20509:-1002:175
	//		数据示例：1627207:28341:黑色云云;1627207:28320:白色云云;20509:28383:均码消散
	//		数据解读：颜色:原颜色代码:新名称;
	//		特别说明：1627207固定表示颜色，20509固定表示尺码
			StringBuilder cusprop = new StringBuilder();
			String color;
			String size;
			for(int i=0;i<sellinfo.size();i++){
				SellInfo selli = sellinfo.get(i);
				if(!propmap.containsKey(selli.getColor())){
					color = "1627207:"+i+":"+selli.getColor()+"好看;";
					propmap.put(selli.getColor(), "1627207:"+i);
					cusprop.append(color);
				}
				if(!propmap.containsKey(selli.getSize())){
					size = "20509:"+i+":"+selli.getSize()+";";
					propmap.put(selli.getSize(), "20509:"+i);
					cusprop.append(size);
				}
			}
			tbm.setInput_custom_cpv(cusprop.toString());
			
			
	//		销售属性组合
	//		数据示例：29:1111:bm1:1627207:28341;20518:10122;29.1:2222:bm2:1627207:3232482;20518:10122;
	//		数据解读：价格1:数量1:编码1:颜色:颜色代码1;尺码:尺码代码1;
			StringBuilder sellinfocombi = new StringBuilder();
			for(int i=0;i<sellinfo.size();i++){
				SellInfo selli = sellinfo.get(i);
				sellinfocombi.append(selli.getPrice() / 100).append(":")
				.append(selli.getNumber()).append("::").append(propmap.get(selli.getColor()))
				.append(";").append(propmap.get(selli.getSize())).append(";");
				
			}
			tbm.setSkuProps(sellinfocombi.toString());
			
		}
		
		
//		60c988cac3464724bb953ce27ae5aab2:1:0:|img.alicdn.com/bao.jpg;6e7ed6dc03bc98c01b71e0ed3e2e6819:1:1:|img.alicdn.com/bao/uploaded/i4.jpg;34e70e9f31f890e1cccf698b20fd9dcd:2:0:1627207:3232480|img.alicdn.com/bao/uploaded/i2/TB1Q.jpg;60c988cac3464724bb953ce27ae5aab2:2:0:1627207:28338|img.alicdn.com/bao/uploaded/i2/TB1.jpg
//		图片在本地的文件名的md5值：1表示主图，2表示颜色图片：0表示显示顺序：|url为主图在我的淘宝图片空间的地址
		StringBuilder picture = new StringBuilder();
		StringBuilder picturestatus = new StringBuilder();
		
		if(imgs!=null && imgs.size()>0){
			int j = 0; // 用以表示主图的顺序
			for(int i=0;i<imgs.size();i++){
				ImgModel img = imgs.get(i);
				if(StringUtils.isNotBlank(img.getColor())){// 颜色图片
					picture.append(img.getImgNamemd5()).append(":2:0:").append(propmap.get(img.getColor())).append(":|;");
					picturestatus.append("2;");
				}
				picture.append(img.getImgNamemd5()).append(":1:").append(i).append(":|;");
				picturestatus.append("2;");
			}
		}
		tbm.setPicture(picture.toString());
		tbm.setPicture_status(picturestatus.toString());
		tbm.setUpload_fail_msg(200);
		
		return tbm;
	}
	
	

	
}
