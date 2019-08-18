package com.ny.mm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {
	
	
	@RequestMapping("/member/mypage")
	public String mypage(HttpSession session) {
		
		String view = "member/mypage"; 

		if(session == null || session.getAttribute("loginInfo") == null) {
			view = "redirect:/login/form";
		}
		
		return view;
	}
}
