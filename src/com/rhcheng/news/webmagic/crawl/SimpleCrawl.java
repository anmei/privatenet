package com.rhcheng.news.webmagic.crawl;

import java.util.HashMap;
import java.util.Map;

import com.rhcheng.util.UtilClient;

public class SimpleCrawl {
	public static void main(String[] args) {
//		String url="https://upload.taobao.com/auction/json/reload_cats.htm?customId=1";
		String url="https://item.taobao.com/item.htm?spm=a21cv.7771689.114489.22.Pg4wyY&id=527027984291";
		Map<String,String> formDate = new HashMap<String,String>();
		Map<String,String> headers = new HashMap<String,String>();
		String charset = "GB2312";
		
		formDate.put("path", "next");
		formDate.put("sid","1801");
		headers.put("Cookie", "uc1=cookie14=UoWyiPoJbJt/8g==&existShop=false&cookie16=Vq8l+KCLySLZMFWHxqs8fwqnEw==&cookie21=UIHiLt3xSarfJWFC &tag=0&cookie15=U+GCWk/75gdr5Q==&pas=0; v=0; cookie2=1c69e51a6b05095777992627b11ea377; _tb_token_=XUTJTPkLNGjucz3; existShop=MTQ1NjA0NTU5NA==; sg=o9d; cookie1=AQCY4gnNfVfOJben3oPzwFoyh4zwDml2YZQz4sgqyUs=; unb=2401205659; skt=add0381ace30ac7d; _l_g_=Ug==; _nk_=anmeigogogo; cookie17=UUwQl69T+VQk6A==");
		headers.put("User-Agent", "Mozilla/5.0");
		headers.put("Content-Type", "application/x-www-form-urlencoded");
//		String res = UtilClient.post(url, formDate, headers,charset);
		String res = UtilClient.get(url,null,charset);
		System.out.println(res);
		
	}
	
}
