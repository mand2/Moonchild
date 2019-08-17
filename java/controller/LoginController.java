package com.ny.mm.controller;
/*-------------------
 * 파일이름: LoginController.java
 * 파일설명: 회원 로그인 
 * 작성자: 김나연
 * 버전: 1.0.0
 * 생성일자: 2019-08-06 오전 10시 57분
 * 최종수정일자: 2019-08-06 오전 10시 57분
 * 최종수정자: 김나연
 * 최종수정내용: member manager 스프링으로 변경 
 * @form() : get방식으로 올 때 로그인폼 반환
 * @login() : post방식으로 form action 가면 로그인 프로세스 실행
 * -------------------*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ny.mm.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginservice;
	
	
	@RequestMapping(value = "/login/form" , method = RequestMethod.GET)
	public String form( HttpServletRequest request) {
		
		String view = "login/form";
		
		HttpSession session = request.getSession(false); //없으면 널값 반환.
		
		//로그인이 이미 되어있으면 홈으로
		if(session != null && session.getAttribute("loginInfo") != null) {
			view = "redirect:/home";
		}
		
		return view;
	}
	
	@RequestMapping(value = "/login/form", method = RequestMethod.POST)
	public String login(@RequestParam(value = "id")String id, @RequestParam(value = "pw") String pw,
					HttpServletRequest request) {
		
		boolean loginChk = false;
		int result = 0;
		

		//login 성공시 바로 홈으로 리다이렉트
		String view = "login/fail"; 
		loginChk = loginservice.login(id, pw, request);
		
		if(loginChk) {
			view = "redirect:/home";
		}
		
		return view;
	}
	
	
}
