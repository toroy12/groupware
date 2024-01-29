package com.groupware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groupware.dto.Employee;

@Mapper
public interface AdminMapper {
	
	public List<Employee> employeeList();

}
