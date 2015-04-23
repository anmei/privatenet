package com.rhcheng.base;

import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

/**
 *	simplejdbc 工厂
 * 
 * @author RhCheng
 *
 */
@Component("simpleJdbcCallFactory")
public class SimpleJdbcCallFactory  {
	
	private DataSource dataSource;

	public SimpleJdbcCall getObject() {
		return new SimpleJdbcCall(dataSource);
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


}
