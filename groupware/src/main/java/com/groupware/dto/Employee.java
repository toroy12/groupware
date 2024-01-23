package com.groupware.dto;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class Employee {
	
	public String id;
	public String pw;
	public String name;
	public String gender;
	public String department;
	public String departmentName;
	public String position;
	public String positionName;
}
