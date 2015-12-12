package com.rhcheng.user.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {    
	@Override    
	public Date convert(String source) {    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	    dateFormat.setLenient(false);    
	    try {
	    	if(StringUtils.isNotBlank(source)){
	    		return dateFormat.parse(source);
	    	}else {
	    		return null;
	    	}
	    } catch (ParseException e) {    
	        e.printStackTrace();    
	    }           
	    return null;    
	}    
}
