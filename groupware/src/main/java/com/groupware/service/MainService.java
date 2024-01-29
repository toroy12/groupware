package com.groupware.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.groupware.mapper.MainMapper;

import lombok.NoArgsConstructor;

@Service
@Transactional
@NoArgsConstructor
public class MainService {

	private final MainMapper mainMapper;
	
	public MainService(MainMapper mainMapper) {
		this.mainMapper = mainMapper;
	}
	
	public String signIn(String id, String pw) {
		
		String result = "";
		int checkId = mainMapper.checkId(id);
		String checkPw = mainMapper.checkPw(id);
		
		if(checkId == 0) {
			result = "WrongId";
		}else {
			if(checkPw.equals(pw)) {
				if(mainMapper.checkApproval(id) == 'Y') {
					result = "SuccessLogIn";
				}else {
					result = "NotYetApproval";
				}
			}else {
				result = "WrongPw";
			}
		}
		
		return result;
	}
	
}
