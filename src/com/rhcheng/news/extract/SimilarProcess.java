package com.rhcheng.news.extract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * judge how much similarity between two article
 * @author RhCheng
 * @date   2014-9-11
 */
public class SimilarProcess {
	private SimilarProcess(){}
	private static SimilarProcess sp;
	public static SimilarProcess getInstance(){
		if(null == sp){
			sp = new SimilarProcess();
		}
		return sp;
	}
	
	/**
	 * get the candidate elements
	 * @author RhCheng
	 * @date 2014-9-11
	 * @param str
	 * @return a list in which is the candidate
	 */
	public List<String> getCandidate(String str){
		String regex = ".{1}(。|，|；|？|！|……|——|、|“|”|《|》|：|（|）){1}.{0,1}";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(str);
        List<String> res = new ArrayList<String>();
        while(matcher.find()){
        	res.add(matcher.group());
        }
        return res;
        
	}
	
	/**
	 * core method,calculate how many same good block with the {@code li1},
	 * suggest: make the longer list as {@code li1},because the similarity is 
	 * based on the longer list 
	 * note: only when the same elements size is bigger than 2,then the block is a good block
	 * @author RhCheng
	 * @date 2014-9-11
	 * @param li1 a list which size is bigger
	 * @param li2 a list which size is smaller
	 * @see #getSimilarity(String, String)
	 * @return a list in which is all the same block between {@code li} and {@code li2}
	 */
	public List<String> getAllSameBlock(List<String> li1,List<String> li2){
		List<String> res = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		int vicestart = 0;
		int samecount = 0;
		int vicecircu = 0;
		
		for(int i=0;i<li1.size();i++){
			vicestart=0;
			samecount=0;
			vicecircu=0;
			sb.setLength(0);
			for(int k=i;k<li1.size();k++){
				for(int j=vicestart;j<li2.size();j++){
					if(samecount>0 && !li1.get(k).equals(li2.get(j))){
						if(samecount>=2){
							res.add(sb.toString());
						}
						vicecircu++;
						vicestart=vicecircu;
						k=i;
						samecount=0;
						sb.setLength(0);
						break;
					}
					if(li1.get(k).equals(li2.get(j))){
						vicestart = j+1;
						samecount++;
						sb.append(li1.get(k));
						break;
					}
				}
				if(vicecircu>0 || vicestart>=li2.size()){//break the base list circle when the good block has appeared once
//				if(vicestart>=li2.size()){// only when all the vice list had been circled then break the base circle
					break;
				}
			}
			
			if(samecount>=2){
				res.add(sb.toString());
			}
			
		}
		
		return res;
	}
	
	
	
	
	/**
	 * get the similarity between {@code str1} and {@code str2}
	 * @author RhCheng
	 * @date 2014-9-11
	 * @param str1
	 * @param str2
	 * @return a string
	 */
	public String getSimilarity(String str1,String str2){
		List<String> li1 = getCandidate(str1);
		List<String> li2 = getCandidate(str2);
//		List<String> tem;
//		if(li1.size()<li2.size()){
//			tem=li1;
//			li1=li2;
//			li2=tem;
//		}
		
		BigDecimal bd1 = new BigDecimal(getAllSameBlock(li1, li2).size())
						.divide(new BigDecimal(li1.size()-1),2,BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100));
		
		BigDecimal bd2 = new BigDecimal(getAllSameBlock(li2, li1).size())
						.divide(new BigDecimal(li2.size()-1),2,BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(100));
		
//		System.out.println(bd1+" "+bd2);
		
		return (bd1.max(bd2)+"%");
	}
	
	
	
	public static void main(String[] args){
		String str2 = "周杰伦自从大方坦承将在2015年1月，也就是36岁前结婚之后，外界都在等待他和昆凌的好消息，最近还爆出两人结婚消息，但他所属的杰威尔公司则出面否认传言。最近小两口到淡水别墅过中秋。周董和女友又亲又抱，大方在母亲大人面前秀恩爱，看来女友已经通过婆婆这一关。根据媒体报道，周杰伦在中秋节当天，和昆凌在传说中同居的新房现身。晚间烤肉趴开始后，小两口大多待在室内，两人一下是隔空玩亲亲，一下是贴面跳舞玩得好开心……而这一切在周妈眼里似乎很平常。";
		String str1 = "周杰伦自从大方坦承将在2015年1月，也就是36岁前结婚之后，外界都在等待他和昆凌的好消息，最近还爆出两人结婚消息，但他所属的杰威尔公司则出面否认传言。最近小两口到淡水别墅过中秋。周董和女友又亲又抱，大方在母亲大人面前秀恩爱，看来女友已经通过婆婆这一关。根据媒体报道，周杰伦在中秋节当天";
		System.out.println(SimilarProcess.getInstance().getSimilarity(str2, str1));
		
//		for(String s:li1){
//			System.out.println(s);
//		}
		
	}
	
	
}
