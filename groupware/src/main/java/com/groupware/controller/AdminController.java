package com.groupware.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.groupware.dto.Employee;
import com.groupware.dto.LoginInfo;
import com.groupware.mapper.AdminMapper;

import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class AdminController {
	
	private final AdminMapper adminMapper;
	
	public AdminController(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	
	@GetMapping("employeeList")
	public String EmployeeList(Model model, HttpSession session) {
		
		List<Employee> employeeList = adminMapper.employeeList();
		LoginInfo loginInfo = (LoginInfo) session.getAttribute("EMPLOYEE_INFO");
		
		model.addAttribute("title", "사원목록");
		model.addAttribute("employeeList", employeeList);
		model.addAttribute("loginInfo", loginInfo);
		
		return "admin/employeeList";
	}
}
