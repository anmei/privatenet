package com.rhcheng.user.service.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rhcheng.baseJqgrid.JqGridSearch;
import com.rhcheng.common.Pagination;
import com.rhcheng.user.dao.StudentDao;
import com.rhcheng.user.entity.Student;
import com.rhcheng.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource(name="studentDao")
	private StudentDao studentDao;
	
	
	@Override
	public Pagination<Student> getStudentList(int page, int pageItems,
			JqGridSearch query) {
		// TODO Auto-generated method stub
		return this.studentDao.getStudentList(page, pageItems, query);
	}


	@Override
	public int update(Student stu) {
		System.out.println("ahhahha……");
		return 0;
	}

}
