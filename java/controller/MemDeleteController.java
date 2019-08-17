package com.ny.mm.controller;
/* 2019-08-07
 * (관리자용)
 * 멤버 삭제: 회원 전체 목록에서 볼 때.
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ny.mm.service.MemDelService;

@Controller
public class MemDeleteController {
	
	@Autowired
	private MemDelService deleteService;
	
	
	
	/* mapping: /member/delete?id=${member.id} */
	@RequestMapping(value = "/member/delete")
	public String delete(@RequestParam(value = "id") String id, Model model) {
		
		model.addAttribute("result", deleteService.delete(id));
		
		return "redirect:/member/list";
	}
	
	
	//rest방식으로 삭제
	@RequestMapping("/member/delete/{id}")
	public String delete(@PathVariable(value = "id")String id) {
		deleteService.delete(id);
		return "redirect:/member/list";
	}
}
