package com.rhcheng.user.service;
import com.rhcheng.baseJqgrid.JqGridSearch;
import com.rhcheng.common.Pagination;
import com.rhcheng.user.entity.Student;

public interface UserService {
	/**
	 * 获取学生列表
	 * @param page
	 * @param pageItems
	 * @param query
	 * @return
	 */
	public Pagination<Student> getStudentList(int page, int pageItems,JqGridSearch query);
	
	public int update(Student stu); 
	
}
