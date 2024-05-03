package com.groupware.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groupware.dto.Comment;
import com.groupware.dto.LoginInfo;
import com.groupware.dto.Message;
import com.groupware.dto.NoticeBoard;
import com.groupware.mapper.EmployeeMapper;

import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@NoArgsConstructor
@Log4j
public class EmployeeController {
	
	private final EmployeeMapper employeeMapper;
	
	public EmployeeController(EmployeeMapper employeeMapper) {
		this.employeeMapper = employeeMapper;
	}
	
	@GetMapping("/noticeBoard")
	public String noticeBoard(Model model, HttpSession session) {
		
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		List<NoticeBoard> noticeBoardList = employeeMapper.noticeBoardList(); 
		
		model.addAttribute("title", "공지사항");
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("noticeBoardList", noticeBoardList);
		
		return "employee/noticeBoard";
	}
	
	@GetMapping("/addNoticeBoard")
	public String addNoticeBoard(Model model, HttpSession session) {
		
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		
		model.addAttribute("title", "공지사항 작성");
		model.addAttribute("loginInfo", loginInfo);
		
		return "employee/addNoticeBoard";
	}
	
	@PostMapping("/addNoticeBoard")
	public String addNoticeBoard(String id, String title, String content, String allowComment) {
		
		char c = 'N';
		
		if(allowComment != null) {
			c = 'Y';
		}
		
		employeeMapper.addNoticeBoard(title, id, content, c);
		
		return "redirect:/noticeBoard";
	}
	
	@GetMapping("/detailNoticeBoard")
	public String detailNoticeBoard(@RequestParam(value="boardId") int boardId, Model model, HttpSession session) {
		
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		NoticeBoard detailNoticeBoard = employeeMapper.detailNoticeBoard(boardId);
		List<Comment> commentList = employeeMapper.commentList(boardId);
		
		model.addAttribute("title", detailNoticeBoard.title);
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("detailNoticeBoard", detailNoticeBoard);
		model.addAttribute("commentList", commentList);
		
		return "employee/detailNoticeBoard";
	}
	
	@GetMapping("/modifyNoticeBoard")
	public String modifyNoticeBoard(@RequestParam(value="boardId") int boardId, Model model, HttpSession session){
		
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		NoticeBoard detailNoticeBoardByBoardId = employeeMapper.detailNoticeBoardByBoardId(boardId);
		
		model.addAttribute("title", "공지사항 수정");
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("detailNoticeBoardByBoardId", detailNoticeBoardByBoardId);
		
		return "employee/modifyNoticeBoard";
	}
	
	@PostMapping("/modifyNoticeBoard")
	public String modifyNoticeBoard(int boardId, String title, String content, String allowComment) {
		
		char c = 'N';
		
		if(allowComment != null) {
			c = 'Y';
		}
		
		employeeMapper.modifyNoticeBoard(boardId, title, content, c);
		
		return "redirect:/detailNoticeBoard?boardId=" + boardId;
	}
	
	@GetMapping("/deleteNoticeBoard")
	public String deleteNoticeBoard(@RequestParam(value="boardId") int boardId) {
		
		employeeMapper.deleteNoticeBoard(boardId);
		
		return "redirect:/noticeBoard";
	}
	
	@PostMapping("/comment")
	public String comment(HttpSession session, int boardId, String commentContent){
		
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		
		employeeMapper.comment(loginInfo.id, boardId, commentContent);
		
		return "redirect:/detailNoticeBoard?boardId=" + boardId;
	}
		
	
	
	@GetMapping("calendar")
	public String calendar(Model model, HttpSession session) {
		
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("title", "calendar");
		
		return "employee/calendar";
	}
	
	@GetMapping("/message")
	public String message(Model model, HttpSession session) {
		
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		
		List<Message> messageList = employeeMapper.messageList(loginInfo.id);
		
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("messageList", messageList);
		model.addAttribute("title", "message");
		
		return "employee/messageList";
	}
	
	@GetMapping("/writeMessage")
	public String writeMessage(Model model, HttpSession session) {
		
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("title", "메일 작성");
		
		return "employee/writeMessage";
	}
	
	@PostMapping("wrtieMessage")
	public String writeMessage(String recipient, String title, String content, HttpSession session) {
		
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		
		employeeMapper.writeMessage(loginInfo.id, recipient, title, content);
		
		return "redirect:/message";
	}
	
	@GetMapping("detailMessage")
	public String detailMessage(Model model, HttpSession session, @RequestParam(value="id") int id) {
		
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		
		Message detailMessage = employeeMapper.detailMessage(id);
		
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("detailMessage", detailMessage);
		model.addAttribute("title", "메일 보기");
		
		return "employee/detailMessage";
	}
	
	@GetMapping("/deleteMessage")
	public String deleteMessage(@RequestParam(value="id") int id) {
		
		employeeMapper.deleteMessage(id);
		
		return "redirect:/message";
	}
}
