package com.groupware.dto;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class Message {
	
	public int id;
	public String writer;
	public String title;
	public String content;
	public String createdDate;

}
