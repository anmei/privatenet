package com.rhcheng.user.entity;

import java.sql.Date;

public class Student {
	private Integer studentId;
	private String studentName;
	private Date studentBirthday;
	private Integer classId;
	
	public Student(){}
	
	public Student(Integer studentId, String studentName, Date studentBirthday,
			Integer classId) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentBirthday = studentBirthday;
		this.classId = classId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Date getStudentBirthday() {
		return studentBirthday;
	}
	public void setStudentBirthday(Date studentBirthday) {
		this.studentBirthday = studentBirthday;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}

}
