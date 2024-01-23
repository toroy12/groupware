package com.groupware.dto;

import groovy.transform.ToString;
import lombok.Getter;

@Getter
@ToString
public class LoginInfo {
	
	public LoginInfo(String name, String departmentName, String positionName) {
		this.name = name;
		this.departmentName = departmentName;
		this.positionName = positionName;
	}
	
	public String name;
	public String departmentName;
	public String positionName;

}
