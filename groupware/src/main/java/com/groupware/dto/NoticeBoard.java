package com.groupware.dto;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class NoticeBoard {
	
	public int boardId;
	public String title;
	public String writer;
	public String writerName;
	public String departmentName;
	public String positionName;
	public String createdDate;
	public String content;
	public char allowComment;
}
