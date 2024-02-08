package com.groupware.dto;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class Comment {
	
	public int commentId;
	public String departmentName;
	public String name;
	public String positionName;
	public String createdDate;
	public int boardId;
	public String commentContent;
	public int depth;

}
