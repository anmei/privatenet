
package com.rhcheng.news.extract;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 正文提取算法(只需线性时空复杂度即可完成)：
 * 【图片标记、正文开始标记、间隔计数器(当出现过正文结束标记且正文开始标记未出现时用于计数扫描document的行数)、是否有过结束标记】
 * 
 * 0、循环document每次取其一行数据
 * 1、判断是否出现了形如12:45格式的发布日期，如已出现则执行2，如果未出现则执行0；
 * 
 * 正文图片处理(判断某张图片是否为正文图片)：
 * 2、如果图片标记为假 且 （该行出现了类似12:45格式的日期或者正文开始行标记为真），则图片标记置为真，继续执行3；
 *   否则继续执行3；
 * 3、如果图片标记为真 且 该行包含图片tag 且  间隔计数器<3，则如果该行不包含垃圾信息将其添加到结果集，继续执行0；
 *   否则继续执行4；
 * 
 * 正文文本处理
 * 4、如果正文起始行未出现 且 该行包含points之一，且(本行字符总数>=singleLength或者向下2行(包括本行共3行)总字符数>=threshold)，则标记该行为正文起始行，将间隔计数器置为0，如果该行不包含垃圾信息将其添加到结果集，继续执行0；
 *   否则，继续执行5；
 * 5、如果有过结束标记 且 正文还未开始，则间隔计数器++，并判断间隔是否超出某个值，如果超过了则终止该算法，如果未超过则继续执行6；
 *   否则继续执行6；
 * 6、 如果正文起始行已出现：
 * 如果该行为空 或者 (该行非空且该行不包含commonPoints，且(下1行为空 或者 下1行不包含任意常见标点commonPoints))，则该行的上一行为该部分正文结束行，继续执行0；
 * 如果该行非空 且 该行包含commonPoints，且(下1行为空 或者 下1行不包含任意常见标点)，则该行为该部分正文结束行,则如果该行不包含垃圾信息将其添加到结果集,继续执行0；
 * 否则，如果该行不包含垃圾信息将其添加到结果集,继续执行0；
 * 
 * 垃圾图片处理
 * 7、  获取最终获取到的正文中所有img标签，循环判断是否为垃圾图片，如果是则删除之。
 * 
 * 
 * @author RhCheng
 * @date   2014-9-11
 *
 */
public class TextExtract {
	
	//---------------------------------------------------------------static class values
	private static String urlstr;// url
	private static String html; // document
	private static StringBuilder text = new StringBuilder();// result which will return 
	private static List<String> lines = new ArrayList<String>(); // all the contents store in a list by line
//	private static ArrayList<Integer> indexDistribution = new ArrayList<Integer>();// the length of every block
//	private static boolean flag;
	
	
	
	//-----------------------------------------------------------------core algorithm values
	
	private static int blocksWidth = 2; // factly, unit to confirm if is the main body is {@code blockWidth+1}
	private static int threshold = 60 ; // block length to be the start line
	private static int singleLength = 20;// single line length to be start line
	private static String[] points = new String[]{"，","。"}; //punctuation start line must contain 
	private static String[] commonPoints = new String[]{"，",",","。",".","：",":","……","“","\"","”","！","!","？","?","——","、"};// common sentence must contain these punctuation
	
	// unusable images
	private static String[] rubbishImgs = new String[]{"wangmeng.baidu.com","ubmcmm.baidustatic.com","img.emarbox.com","alicdn.com","taobaocdn.com","mmcdn.cn"
		,"img.northnews.cn/images/khdnr.jpg","upload.northnews.cn/2013/1221/1387609426965.jpg","upload.northnews.cn/2014/0523/1400827123373.jpg"
		,"hiphotos.baidu.com/news","video.chinatimes.com/images","www.chinatimes.com/paperimg",
		"Logo.gif","logo.png","logo.jpg","indeccode.png","ico.gif","client.gif"};// rubbish images
	
	// unusable text
	private static String[] rubbishtext = new String[]{"版权声明","版权所有","客服电话","Copyright","All Rights Reserved","许可证编号","联系我们"
		,"分享到人人","分享到qq","分享到QQ"};
	
	/**图片用relative路径的网站*/
//	http://news.xkb.com.cn/
	
	
	
	//----------------------------------------------------------------------------static class method
	
	public static void setthreshold(int value) {
		threshold = value;
	}

	
//	public static String parse(String _html) {
//		return parse(_html, false);
//	}
	
	public static String parse(String _html,String url) {
		html = _html;
		urlstr = url;
		preProcess();
//		System.out.println(html);
		return getText();
	}
	
	/**
	 * remove noise
	 */
	private static void preProcess() {
		html = html.replaceAll("(?is)<!DOCTYPE.*?>", "");
		html = html.replaceAll("(?is)<head.*?>.*?</head>", "");		// remove head
		html = html.replaceAll("(?is)<!--.*?-->", "");				// remove html comment
		html = html.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
		html = html.replaceAll("(?is)<style.*?>.*?</style>", "");   // remove css
		html = html.replaceAll("(?is)<(?!img|p|/p).*?>", "");		// remove the tag except {@code p}{@code img}
		html = html.replaceAll("&.{2,5};|&#.{2,5};", " ");			// remove special char
		
		//<!--[if !IE]>|xGv00|9900d21eb16fa4350a3001b3974a9415<![endif]--> 
	}
	
	
	/**
	 * the core method implementation
	 * @return
	 */
	private static String getText() {
		lines = Arrays.asList(html.split("\n"));
		text.setLength(0); // result
		
		//int start = -1; int end = -1;boolean boolend = false;
		// an counter means how many lines had been processed before the {@code start} line appear and the {@code end} line processed
		boolean boolstart = false; //mark if the main body has begin 
		int cout = 0; // interval value
		boolean ifImage = false; // indicate if the images appearing later should retain
		boolean hasanend = false;
		boolean ifperfect = false;// mark if the date had appeared,only had appeared then begin the algorithm
		
		for (int i = 0; i < lines.size(); i++) {
			// guarantee main body is below the date like 45:12
			if(ifperfect){
//				System.out.println(lines.get(i)+boolstart);
//				if( !ifImage && (ifDateTime(lines.get(i)) || boolstart ) ){
//					ifImage = true;
//				}
				
				/** images process*/
				ifImage = true;
				
				
				if(ifImage && lines.get(i).contains("<img") && cout<5){
					if(!ifContainStr(lines.get(i), rubbishtext)){
						text.append(lines.get(i));
					}
					continue;
				}
				
				
				/** text process*/
				// judge if this line is start line
				if (!boolstart
						&& containNums(lines.get(i), points)>=1
						&& (getNumsOfThisLine(i, lines)>=singleLength || getNumsOfSomeLine(i, blocksWidth, lines)>=threshold)) {
					boolstart = true;
					cout = 0;
					if(!ifContainStr(lines.get(i), rubbishtext)){
						text.append(lines.get(i));
					}
					continue;
				}
				
				
				if(hasanend && !boolstart){
					cout++;
					if(urlstr.contains("northnews.cn")){// 正北方网
						if(cout>110){
							break;
						}
					}else if(urlstr.contains("cankaoxiaoxi.com")){// 参考消息网
						if(cout>70){
							break;
						}
					}else if(cout>50){
						break;
					}
					
				}
				
				if (boolstart){
					if(ifBlank(lines.get(i)) 
							|| (!ifBlank(lines.get(i)) && containNums(lines.get(i), commonPoints)<1 && (ifallblankOfSomeLine(i+1, 0, lines) || (!ifContainImg(lines.get(i+1)) && containNums(lines.get(i+1), commonPoints)<1 ))) ){
						boolstart= false;
						hasanend = true;
					
					}else if(!ifBlank(lines.get(i)) && containNums(lines.get(i), commonPoints)>=1  && (ifallblankOfSomeLine(i+1, 0, lines) || (!ifContainImg(lines.get(i+1)) && containNums(lines.get(i+1), commonPoints)<1) )){
						boolstart= false;
						hasanend = true;
						if(!ifContainStr(lines.get(i), rubbishtext)){
							text.append(lines.get(i));
						}
					}else{
						if(!ifContainStr(lines.get(i), rubbishtext)){
							text.append(lines.get(i));
						}
					}
					
				}

			
			}else if(ifDateTime(lines.get(i))){ifperfect = true;}
			
		}
		
		/** remove the rubbish images*/
		String resstr = text.toString();
		List<String> allImgs = getImgTag(resstr);
		for(String li:allImgs){
			// remove unusable images
			for(int k=0;k<rubbishImgs.length;k++){
				if(li.contains(rubbishImgs[k])){
					resstr = resstr.replace(li, "");
					break;
				}
			}
		}
		
		// process images width and height
		resstr=resstr.replace("width", "w");
		resstr=resstr.replace("height", "h");
		resstr=resstr.replace("alt_src", "src");
		
		return resstr;
	}
	
	
	public static Document getDocument(String url) throws IOException{
		Connection con = Jsoup.connect(url)
				.data("jquery", "java")
				.userAgent("Mozilla")
				.cookie("auth", "token")
				.timeout(60000);
		Document doc = con.get();
		//System.out.println("get document ok……");
		return doc;
	}
	
	
	public static String getContent(String url) throws IOException{
		Document doc = getDocument(url);
		///response.setCharacterEncoding(con.response().charset());
		String text = parse(doc.toString(),url);
		//System.out.println("get content ok……");
		return text.trim();

	}
	
	public static String getContent(String url,Document doc) throws IOException{
		///response.setCharacterEncoding(con.response().charset());
		String text = parse(doc.toString(),url);
		//System.out.println("get content ok……");
		return text.trim();

	}
	
	
	//--------------------------------------------------------------------------common utils
	
	/**
	 * caculate how many same chars in the {@code temstr} with the {@code data}
	 * @param temstr an String
	 * @param data an array 
	 * @return the number of same chars
	 */
	public static int containNums(String temstr,String[] data){
		int res = 0;
		String str = getClearStr(temstr);
		for(int i=0;i<str.length();i++){
			for(int j=0;j<data.length;j++){
				if(str.charAt(i) == data[j].charAt(0)){
					res++;break;
				}
			}
		}
		return res;
	}
	
	/**
	 * judge if the {@code temstr} contain a string in {@code data}
	 * @author RhCheng
	 * @date 2014-9-11
	 * @param temstr
	 * @param data
	 * @return
	 */
	public static boolean ifContainStr(String temstr,String[] data){
		String str = getClearStr(temstr);
		for(int j=0;j<data.length;j++){
			if(str.contains(data[j])){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * get the block size after remove all the tag and blank space
	 * @param indexStart line index to begin 
	 * @param li
	 * @return
	 */
	public static int getNumsOfThisLine(int indexStart,List<String> li){
		return getNumsOfSomeLine(indexStart, 0, li);
	}
	
	public static int getNumsOfSomeLine(int indexStart,int space,List<String> li){
		int sum = 0;
		for(int i=indexStart;i<li.size()&&i<=indexStart+space;i++){
			sum += getClearStr(li.get(i)).trim().length();
		}
		return sum;
	}
	
	/**
	 * judge if somelines(index from {@code indexStart} to {@code indexStart}+{@code +space} ) are all blank
	 * note: if the line (with index {@code indexStart}) is out of {@code li}'s size,the method 
	 * will return {@code true}
	 * 
	 * @author RhCheng
	 * @date 2014-9-10
	 * @param indexStart
	 * @param space
	 * @param li
	 * @return
	 */
	public static boolean ifallblankOfSomeLine(int indexStart,int space,List<String> li){
		if(indexStart>li.size()-1){
			return true;
		}else{
			boolean flag = true;
			int res = 0;
			for(int i=indexStart;i<li.size()&&i<=indexStart+space;i++){
				if(ifBlankExcepImg(li.get(i))) res++;
			}
			if(res < space+1){
				flag = false;
			}
			return flag;
		}
		
	}
	
	
	public static String getClearStr(String str){
		String temstr = str;
		return temstr.replaceAll("(?is)<.*?>", "");
	}
	
	public static String getStrAfterRemoveImg(String str){
		String temstr = str;
		return temstr.replaceAll("(?is)<img.*?>", "");
	}
	
	public static String getRemovedStrExcepImg(String str){
		String temstr = str;
		return temstr.replaceAll("(?is)<(?!img).*?>", "");
	}
	
	/**
	 * whether the {@code str} is blank after remove all the tag except img tag
	 * @author RhCheng
	 * @date 2014-9-11
	 * @param str
	 * @return
	 */
	public static boolean ifBlankExcepImg(String str){
		return getRemovedStrExcepImg(str).trim().equals("") || getRemovedStrExcepImg(str) == null; 
	}
	
	/**
	 * whether the {@code str} is blank after remove all the tag
	 * @author RhCheng
	 * @date 2014-9-11
	 * @param str
	 * @return
	 */
	public static boolean ifBlank(String str){
		return getClearStr(str).trim().equals("") || getClearStr(str) == null;
	}
	
	public static boolean ifContainImg(String str){
		return str.contains("<img");
	}
	
	/**
	 * 
	 * @author RhCheng
	 * @date 2014-9-17
	 * @param str content
	 * @return
	 */
	public static List<String> getImgTag(String str){
		String regex = "(?=)<img.*?>";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);
        List<String> res = new ArrayList<String>();
        while(matcher.find()){
        	res.add(matcher.group());
        }
        
        return res;
        
	}
	
	/**
	 * 
	 * @author RhCheng
	 * @date 2014-9-17
	 * @param str img tag
	 * @return
	 */
	public static String getImgSrc(String str){
		String regex = " src=\"(?<=).*?(?=)\"";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()){
        	return matcher.group();
        }
        return "";
        
	}
	/**
	 * 
	 * @author RhCheng
	 * @date 2014-9-17
	 * @param str img tag
	 * @return
	 */
	public static String getSrcValue(String str){
		String src = getImgSrc(str);
		return src.substring(src.indexOf("\"")+1, src.length()-1);
	}	
	
	/**
	 * 
	 * @author RhCheng
	 * @date 2014-9-17
	 * @param str img tag
	 * @return
	 */
	public static String getImgName(String str){
		String src = getSrcValue(str);
		return src.substring(src.lastIndexOf("/")+1);
	}
	
	
	
	/**
	 * judge if the string {@code str} is a DateTime type 
	 * @author RhCheng
	 * @date 2014-9-10
	 * @param str
	 * @return
	 */
	public static boolean ifDateTime(String str){
		//String regex = "\\d{1,2}:\\d{1,2}";  
		String regex = "\\d{1,2}(-|月)\\d{1,2}";
		Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);  
        if(matcher.find())  
            return true;  
        else  
            return false;  
	}
	
	
	public static void main(String[] atrs){
//		List<String> res = getImgTag("<img src='fds'/><p>fdsfdsf</p><img src='fdsfds'/>");
//		for(String li:res){
//			System.out.println(li);
//		}
		
		String regex = "\\d{1,2}(-|月)\\d{1,2}";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher("12-45");
        if(matcher.find()){
        	System.out.println(matcher.group());
        }
		
		
//		for(int k=0;k<rubbishImgs.length;k++){
//			System.out.println(rubbishImgs[k]);
//		}
		
//		System.out.println(ifContainStr("Yes娱乐 9月10日综合报导   中秋翌日喜讯频传，刘翔宣布结婚、刘若英宣布怀孕、戚薇与李承铉也正式完婚，到处都是喜事。刘翔虽不是娱乐圈中人，但他的婚讯却盖过了其他明星的风头，成为当日的娱乐头版。「富二代」王思聪也来凑热闹，他的一句：「整容改变命运」，引发了网友的热烈讨论。", rubbishtext));
        
		
	}
	
}
