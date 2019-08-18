package com.ny.mm.controller;
/*-------------------
 * 파일이름: JoinController.java
 * 파일설명: 회원가입 
 * 작성자: 김나연
 * 버전: 1.0.0
 * 생성일자: 2019-08-06 오전 10시 57분
 * 최종수정일자: 2019-08-09 오전 10시 57분
 * 최종수정자: 김나연
 * 최종수정내용: member id check 작성
 * -------------------*/

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
/* 2019-08-06
 * member register (==join) 관련 컨트롤러 제작
 * getForm() 회원가입 폼 반환
 * 
 * */
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ny.mm.model.member.JoinMember;
import com.ny.mm.model.member.Member;
import com.ny.mm.service.JoinService;
import com.ny.mm.service.memberService;

@Controller
public class JoinController {
	
	@Autowired
	private JoinService joinservice;
	
	@RequestMapping(value = "/join/form", method = RequestMethod.GET)
	public String getForm() {
		return "join/form";
	}
	
	@RequestMapping(value = "/join/form", method = RequestMethod.POST)
	public String join(Model model, JoinMember joinMember, HttpServletRequest request) {
		
		model.addAttribute("result", joinservice.joinMember(request, joinMember));
		
		//model.addAttribute("member", new Member(joinMember.getId(), joinMember.getPw(), joinMember.getName(), joinMember.getPhoto().getOriginalFilename()));
		
		return "join/join";
	}
	

	/*jsp로 보내지 않고 바로 연결하기*/
	@RequestMapping("/join/idcheck2")
	@ResponseBody
	public String idCheck(@RequestParam("id") String id) {
		
		return joinservice.idCheck2(id);
	}
}
