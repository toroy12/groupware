package com.groupware.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groupware.dto.NoticeBoard;

@Mapper
public interface EmployeeMapper {
	
	public List<NoticeBoard> noticeBoardList();
	
	public int addNoticeBoard(String title, String writer, String content, char allowComment);
	
	public NoticeBoard detailNoticeBoard(int BoardId);
	
	public NoticeBoard detailNoticeBoardByBoardId(int BoardId);
	
	public int modifyNoticeBoard(int boardId, String title, String content, char allowComment);
	
	public int deleteNoticeBoard(int BoardId);

}
