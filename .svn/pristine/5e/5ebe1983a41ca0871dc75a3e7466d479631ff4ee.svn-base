package com.rhcheng.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.rhcheng.base.BaseDAO;
import com.rhcheng.baseJqgrid.JqGridSearch;
import com.rhcheng.baseJqgrid.JqGridSearchFormater;
import com.rhcheng.common.MyConstant;
import com.rhcheng.common.Pagination;
import com.rhcheng.user.entity.Student;

@Repository("studentDao")
public class StudentDao extends BaseDAO{
	public Pagination<Student> getStudentList(int page, int pageItems,JqGridSearch query) {
		String sql = " select * from tb_test_student a where 1=1 ";
		List paramList = new ArrayList();//存放查询参数
		
		//查询条件
		if(query!=null){
			
			sql += query.queryDialog(" and ", paramList,
					//实现接口
					new JqGridSearchFormater() {
				
						//将query中存储的字段转换为适合本sql的字段
						public String dbFieldName(String field) {
							if (field.equals("studentid")) { //字段已全部转换为小写
								return field;
							}
							if(field.equals("studentname")){
								return field;
							}
							return null;
						}

						//根据本sql为条件字段添加合适的前缀
						public String prefix(String field) {
							if (field.equals("studentid")) {
								return "a";
							}
							if (field.equals("studentname")) {
								return "a";
							}
							return "a";
						}

						//获取参数数据
						public Object format(String field, String data) {
							return data;
						}

					});
			
		}
		
		return this.queryForPage(MyConstant.MYSQL_SEARCH,pageItems, page, sql, Student.class, paramList.toArray());
		
	}
}
