package com.groupware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groupware.dto.Department;
import com.groupware.dto.Employee;

@Mapper
public interface MainMapper {
	
	public List<Department> departmentList();
	
	public int signUp(String id, String pw, String name, String gender, String department);
	
	public char checkApproval(String id);
	
	public int checkId(String id);
	
	public String checkPw(String id);
	
	public Employee getEmployeeInfo(String id);

}
