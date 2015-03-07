package com.rhcheng.baseJqgrid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

/**
 * jqgrid查询条件处理类
 * @author RhCheng
 * @date   2014-3-25
 * @since  jdk1.6
 */
public class JqGridSearch {

	// 前台传过来的查询条件，如and、or
	private String groupOp;
	
	// 处理之后的查询条件集合
	private List<SearchRule> rules;
	
	public String getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(String groupOp) {
		this.groupOp = groupOp;
	}

	public List<SearchRule> getRules() {
		return rules;
	}

	public void setRules(List<SearchRule> rules) {
		this.rules = rules;
	}
	
	/**
	 * 对前台传输过来的查询条件进行包装处理以便后台dao层的处理
	 * @param jsonText
	 * @return
	 * @throws JSONException
	 */
	public static JqGridSearch convert(String jsonText) throws JSONException {
		if (jsonText == null || jsonText.length() <= 0) {
			return null;
		}	
		//System.out.println("22=========="+jsonText);
		JqGridSearch search = new JqGridSearch();
		List<SearchRule> rules = new ArrayList<SearchRule>();
		search.setRules(rules);
		Map<String, Object> root = (Map<String, Object>) JSONUtil.deserialize(jsonText);
		search.setGroupOp((String) root.get("groupOp"));
		/*************/
		List<Map> ruleList = (List<Map>) root.get("rules");
		for (Map<String, String> ruleMap : ruleList) {
			SearchRule rule = new SearchRule();
			rule.setField(ruleMap.get("field"));
			rule.setOp(ruleMap.get("op"));
			rule.setData(ruleMap.get("data"));
			rules.add(rule);
		}
		return search;
	}

	/**
	 * 
	 * 返回的是带有where 条件的查询语句（Oracle）
	 * 
	 * @return
	 */
	public String queryDialog(String beginPrefix, List<Object> paramList,
			JqGridSearchFormater formater) {
		StringBuffer sb = new StringBuffer();
		int index = 0;
		for (SearchRule rule : rules) {
			// 解析查询条件与查询参数
			String query = rule.queryDialog(paramList, formater);
			
			if (query != null) {
				if (index != 0) {
					sb.append(" " + groupOp + " ");
				}
				sb.append(query);
			}
			index++;
		}
		if (sb.toString().length() > 0 && beginPrefix != null) {
			sb.insert(0, " "+beginPrefix + " ( ");
			sb.append(" ) ");
		}
		return sb.toString();
	}

	

}






/**
 * 查询条件包装类
 * 默认的类，即包类
 * @author RhCheng
 * @date   2014-3-25
 * @since  jdk1.6
 */
class SearchRule {
	// 查询条件中的原始字段域
	private String field;
	// 查询条件原始运算符
	private String op;
	// 查询条件原始数据
	private String data;

	@SuppressWarnings("unused")
	public String queryDialog(List<Object> paramList,
			JqGridSearchFormater formater) {
		// 通过查询条件处理类处理查询条件
		String _field = formater.dbFieldName(field);
		String prefix = formater.prefix(field);
		Object _data = formater.format(field, data);
		
		String operDialog = null;
		if (field != null && _data != null && data.length() > 0) {
			boolean isFill = true;
			_field = (prefix != null ? (prefix + "." + _field) : _field);
			if (op.equals("eq")) {
				operDialog = _field + " = ? ";
			}else if(op.equals("lt")){
				operDialog = _field + " <? ";
			}else if(op.equals("le")){
				operDialog = _field + " <=? ";
			}else if(op.equals("gt")){
				operDialog = _field + " >? ";
			}else if(op.equals("ge")){
				operDialog = _field + " >=? ";
			}
			else if (op.equals("in")) {
				operDialog = " instr(" + _field + ",?)>0 ";
			} else if (op.equals("ne")) {
				operDialog = _field + " <>?";
			} else if (op.equals("ni")) {
				operDialog = " instr(" + _field + ",?)=0 ";
			} else {
				isFill = false;
			}
			if (isFill) {
				paramList.add(_data);
			}
		}
		return operDialog;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field.trim().toLowerCase();
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op.trim();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	// 查询内容包含通配符时，需要转化
	public static String DealSqlQuery(String returnString) {
		return returnString.replaceAll("[", "[[]").replaceAll("_", "[_]")
				.replaceAll("%", "[%]");
	}

}