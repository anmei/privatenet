package com.rhcheng.util.string;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Random;
import org.apache.commons.lang.StringUtils;
import com.rhcheng.common.SysConstants;
import com.rhcheng.util.date.DateUtils;

/**
 * 提供字符串方面的工具方法
 * @author RhCheng
 * @date   2014-5-23
 * @since  1.0
 */
public class StringUtil extends StringUtils{
	

	/**
	 * 判断一个字符串是否存在于一个字符串数组中
	 * 
	 * @param str
	 *            需要检查的字符串
	 * @param StrArr
	 *            字符串数组
	 * @return
	 */
	public static boolean isInArray(String str, String[] strArr) {
		int flag = 0;
		if (strArr == null) {
			return false;
		}
		for (String string : strArr) {
			if (str.equalsIgnoreCase(string)) {
				flag = 1;
				break;
			}
		}
		return flag == 0 ? false : true;
	}

	/**
	 * 用新字符替换所有旧字符
	 * 
	 * @param str
	 * @param oldChar
	 * @param newChar
	 * @return
	 */
	public static String replaceAll(String str, String oldChar, String newChar) {
		if (isBlank(str) || isBlank(oldChar) || newChar == null)
			return null;

		if (newChar.indexOf(oldChar) >= 0)
			return null;

		for (int i = str.length(); i >= 0; i--) {
			if (str.indexOf(oldChar) >= 0) {
				str = str.replace(oldChar, newChar);
			} else {
				break;
			}
		}
		return str;
	}

	/**
	 * 计算字符串总共包含多少个字符
	 * 
	 * @param str 字符串
	 * @param c 字符
	 * @return
	 */
	public static int findCounts(String str, String c) {
		if (isBlank(str) || isEmpty(c))
			return 0;

		int counts = 0;
		for (int i = str.length(); i >= 0; i--) {
			int pos = str.indexOf(c);
			if (pos >= 0) {
				str = str.substring(0, pos) + str.substring(pos + c.length());
				counts++;
			} else {
				break;
			}
		}
		return counts;
	}

	/**
	 * 把字符串数组拼装成字符串，以指定的字符分隔
	 * 
	 * @param strArray
	 *            字符串数组
	 * @param separator
	 *            分隔符
	 * @return 字符串
	 */
	public static String convertStringArrayToString(String[] strArray,
			String separator) {
		if (strArray != null && strArray.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < strArray.length; i++) {
				sb.append(strArray[i]);

				if (i < strArray.length - 1)
					sb.append(separator == null ? "" : separator);
			}
			return sb.toString();
		} else
			return null;
	}

	/**
	 * 把字符list拼装成字符串，以指定的字符分隔
	 * 
	 * @param strList
	 *            字符串列表
	 * @param separator
	 *            分隔符
	 * @return 字符串
	 */
	public static String convertStringListToString(List<String> strList,
			String separator) {
		if (strList != null && strList.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < strList.size(); i++) {
				sb.append(strList.get(i));

				if (i < strList.size() - 1)
					sb.append(separator == null ? "" : separator);
			}
			return sb.toString();
		} else
			return null;
	}

	
	/**
	 * 将Long数组拼装成字符串
	 * 
	 * @param array
	 * @param separator
	 */
	public static String convertLongArrayToString(Long[] array, String separator) {
		if (array != null && array.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; i++) {
				sb.append(array[i]);
				if (i != array.length - 1)
					sb.append(separator == null ? "" : separator);
			}
			return sb.toString();
		}
		return null;
	}
	
	
	/**
	 * 将字符串数组转换为Long数组
	 * 
	 * @param strArray
	 * @return
	 */
	public static Long[] convertStringArrayToLongArray(String[] strArray) {
		if (strArray != null && strArray.length > 0) {
			Long[] result = new Long[strArray.length];
			for (int i = 0; i < strArray.length; i++) {
				result[i] = Long.valueOf(strArray[i]);
			}
			return result;
		} else
			return null;
	}

	

	/**
	 * 把字符串转换成Long数组
	 * 
	 * @param str
	 * @param separator
	 * @return
	 */
	public static Long[] splitStringToArray(String str, String separator) {
		Long[] result = null;
		if (str != null && str.trim().length() > 0) {
			String[] strArray = str.split(separator);
			if (strArray != null && (strArray.length) != 0) {
				result = new Long[(strArray.length)];
				for (int i = 0; i < strArray.length; i++) {
					result[i] = Long.parseLong(strArray[i].trim());
				}

			}
		}
		return result;
	}

	/**
	 * 替换字符串最前和最后的值
	 * 
	 * @param str
	 * @param c
	 * @param chg
	 * @return
	 */
	public static String replacePrefixAndSuffix(String str, String oldChar,
			String newChar) {
		if (isBlank(str) || isBlank(oldChar) || newChar == null)
			return null;

		if (newChar.indexOf(oldChar) >= 0)
			return null;

		String s = String.valueOf(str);
		for (int i = str.length(); i >= 0; i--) {
			if (s.indexOf(oldChar) == 0) {
				s = newChar + s.substring(oldChar.length());
			} else
				break;
		}
		for (int i = str.length(); i >= 0; i--) {
			if (s.lastIndexOf(oldChar) == (s.length() - oldChar.length())) {
				s = s.substring(0, s.lastIndexOf(oldChar)) + newChar;
			} else
				break;
		}

		return s;
	}

	
	/**
	 * 得到n个随机字符
	 * 
	 * @return n位的字符数组
	 * @since 1.0
	 */
	public static char[] getRandomStr(int n) {
		Random random = new Random();
		char[] codes = new char[n];
		for (int i = 0; i < n; i++) {
			codes[i] = SysConstants.LETTERS.charAt(random.nextInt(56));
		}
		return codes;
	}

	/**
	 * 根据区间产生随机数
	 * @param begindecimal  区间起始值
	 * @param enddecimal	区间结束值
	 * @return
	 */
	public static long getRandom(Integer begindecimal,Integer enddecimal){
		return Math.round(Math.random()*(enddecimal-begindecimal)+begindecimal);
	}

	
	
	/**
	 * 得到UUID
	 * 
	 * @return UUID字符串
	 */
	public static String getUUIDStr() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	
	/**
	 * 根据订单sequence获取订单  sequence每天更新为0，年月日时分秒00001
	 * 格式：2014052112251200001
	 * @author RhCheng
	 * @date 2014-5-13
	 * @param productidseq
	 * @return
	 */
	public static String getOrderid(Long productidseq){
		return DateUtils.formatDate(new Date(), "yyyyMMddHHmmss")+
				(("00000"+String.valueOf(productidseq)).substring(String.valueOf(productidseq).length()));
	}
	
	

	/**
	 * 将iso-8859-1编码中文转换成utf-8编码
	 * 
	 * @author 刘霞
	 * @param src
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeToUTF8Str(String src) {
		String utfStr = null;
		try {
			src = src == null ? "" : src;
			utfStr = new String(src.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return utfStr;
	}

	/**
	 * 重命名文件
	 * 
	 * @param originalName
	 *            原始的完整文件名
	 * @param newName
	 *            新的不含后缀的文件名
	 * @return 完整的新文件名
	 */
	public static String renameFile(String originalName, String newName) {
		newName = newName.replaceAll("-", "");
		return newName + "." + getSuffix(originalName);
	}

	/**
	 * 根据文件名返回后缀
	 * 
	 * @author 鲍宇
	 * @param fileName
	 *            文件名
	 * @return 文件后缀
	 */
	public static String getSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	/**
	 * 将二进制转换成16进制
	 * @method parseByte2HexStr
	 * @param buf
	 * @return
	 * @throws 
	 * @since v1.0
	 */
	public static String parseByte2HexStr(byte buf[]){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < buf.length; i++){
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	
	/**
	 * 将16进制转换为二进制
	 * @method parseHexStr2Byte
	 * @param hexStr
	 * @return
	 * @throws 
	 * @since v1.0
	 */
	public static byte[] parseHexStr2Byte(String hexStr){
		if(hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	
	public static void main(String[] args){
	
		System.out.println(StringUtil.getUUIDStr());
		

	}
	
}
