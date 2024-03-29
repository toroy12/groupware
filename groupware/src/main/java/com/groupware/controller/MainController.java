package com.groupware.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.groupware.dto.Department;
import com.groupware.dto.Employee;
import com.groupware.dto.LoginInfo;
import com.groupware.mapper.MainMapper;
import com.groupware.service.MainService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	private final MainMapper mainMapper;
	private final MainService mainService;
	
	public MainController(MainMapper mainMapper, MainService mainService) {
		this.mainMapper = mainMapper;
		this.mainService = mainService;
	}
	
	@GetMapping("/")
	public String signIn(Model model) {
		
		model.addAttribute("title", "KDH 주식회사");
		
		return "signIn";
	}
	
	@PostMapping("signIn")
	public String signIn(String id, String pw, RedirectAttributes redirectAttributes, HttpSession session) {
		
		String result = "redirect:/";
		String signIn = mainService.signIn(id, pw);
		
		if(signIn.equals("WrongId") || signIn.equals("WrongPw")) {
			redirectAttributes.addFlashAttribute("message", "아이디 또는 비밀번호가 틀렸습니다.");
		}else if(signIn.equals("SuccessLogIn")) {
			Employee employeeInfo = mainMapper.getEmployeeInfo(id);
			LoginInfo loginInfo = new LoginInfo(id, employeeInfo.name, employeeInfo.departmentName, employeeInfo.positionName);
			session.setAttribute("EMPLOYEE_INFO", loginInfo);
			result = "redirect:/index";	
		}else {
			result = "redirect:/notYetApproval";
		}
		return result;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/";
	}
	
	@GetMapping("/signUp")
	public String signUp(Model model) {
		
		List<Department> department = mainMapper.departmentList();
		
		model.addAttribute("title", "KDH 주식회사");
		model.addAttribute("department", department);
		
		return "signUp";
	}
	
	@PostMapping("/signUp")
	public String signUp(String id, String pw, String name, String ymd, String gender, String department) {
		
		mainMapper.signUp(id, pw, name, ymd, gender, department);
		
		return "redirect:/";
	}
	
	@GetMapping("/notYetApproval")
	public String notYetApproval(Model model) {
		
		model.addAttribute("title", "KDH 주식회사");
		
		return "notYetApproval";
	}
	
	@GetMapping("/index")
	public String index(Model model, HttpSession session) {
			
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		
		model.addAttribute("title", "KDH 주식회사");
		model.addAttribute("loginInfo", loginInfo);
		
		return "index";
	}
}
