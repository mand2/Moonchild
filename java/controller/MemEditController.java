package com.ny.mm.controller;
/*-------------------
 * 파일이름: MemEditController.java
 * 파일설명: 회원수정 컨트롤러
 * 작성자: 김나연
 * 버전: 1.0.0
 * 생성일자: 2019-08-07 오후 7시 30분
 * 최종수정일자: 2019-08-07 오후 7시 30분
 * 최종수정자: 김나연
 * 최종수정내용: member manager 스프링으로 변경 
 * 폼을 get / post 방식에 따라 다름
 * @getEditor : get방식일 때 > 서비스객체의 select로 폼 불러옴 
 * @edit : post 방식일 때 > 서비스객체 edit로 DB와 통신.
 * view에서 /member/edit?id=${loginInfo.id} 형식으로 c:url 사용 가능
 * 길게 쓰지 않아도 된다!
 * -------------------*/



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ny.mm.model.Member;
import com.ny.mm.model.member.EditMember;
import com.ny.mm.service.memEditService;

@Controller
public class MemEditController {

	@Autowired
	private memEditService editService;
	
	@RequestMapping(value = "/member/edit", method = RequestMethod.GET)
	public String getEditor(@RequestParam("id")String id, Model model) {
		
		model.addAttribute("member", editService.select(id));
		return "member/edit";
	}
	
	@RequestMapping(value = "/member/edit", method = RequestMethod.POST)
	public String edit(HttpServletRequest request, EditMember edit, Model model) {
		
//		System.out.println("수정확인 " + edit);
//		System.out.println("수정전 파일확인" + edit.getOldFile());
		
		int result = 0;
		result = editService.edit(request, edit, edit.getOldFile());
//		System.out.println("수정후결과확인"+result);
		
		model.addAttribute("result", result);
		
		return "redirect:/member/list";
	}
}
