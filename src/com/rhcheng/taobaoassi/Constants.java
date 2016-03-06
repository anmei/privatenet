package com.rhcheng.taobaoassi;

import java.util.regex.Pattern;

public final class Constants {
	public static String workroot = "D:\\taobao\\datapac\\";
	
	// 注意 . 有可能无法表示回车、换行符
	public static Pattern beibeitemaiimg = Pattern.compile("(?<=pageData.itemImgs =).*\r*\n*.*(?=pageData.brandId =)");
	public static Pattern beibeitemaisku = Pattern.compile("(?<=pageData.sku_data =).*\r*\n*.*(?=pageData.martshowUrl =)");
	public static Pattern beibeitemaitile = Pattern.compile("(?<=pageData.itemTitle =).*\r*\n*.*(?=pageData.itemId =)");
}
