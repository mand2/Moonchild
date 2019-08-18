package com.ny.mm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/*-------------------
 * 파일이름: LoginController.java
 * 파일설명: 회원 리스트 controller
 * 작성자: 김나연
 * 버전: 1.0.0
 * 생성일자: 2019-08-07 오전 9시 50분
 * 최종수정일자: 2019-08-07 오전 9시 50분
 * 최종수정자: 김나연
 * 최종수정내용: member manager 스프링으로 변경 
 -------------------*/


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ny.mm.model.member.ListViewData;
import com.ny.mm.model.member.Member;
import com.ny.mm.model.member.SearchMember;
import com.ny.mm.service.MemListService;

@Controller
public class MemListController {
	
	@Autowired
	private MemListService listService;
	
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String memList(Model model,
						@RequestParam(value = "pno", defaultValue = "1") int pno,
						@RequestParam(value = "type", required = false) String type,
						@RequestParam(value = "keyword", required = false) String keyword
						) {
		
		SearchMember searchMember = null;
		
		if(type != null && keyword != null
				&& !type.isEmpty() && !keyword.isEmpty()
				) {
			searchMember = new SearchMember();
			searchMember.setType(type);
			searchMember.setKeyword(keyword);
		}
		
		System.out.println("======searchMember======b"+searchMember);
		ListViewData list =  listService.getListData(pno, searchMember);
		
		model.addAttribute("list", list);
		return "member/list";
	}
}