package com.ny.mm.controller;
/** 2019-08-14
 * rest api로 바꿔보자!
 * pw는 jsonignore쓰기
 *   /rest-api/members 		GET방식으로 url들어오면 (==select) 
 *   		--> 전체 리스트, 페이징 처리된 리스트 보여주기
 *   
 *   /rest-api/members/{id} GET방식으로 url들어오면 (==select)
 *   		--> 회원 1명의 정보 반환
 *   
 *   /rest-api/members 		POST방식으로 url들어오면 (==insert)
 *   		--> 회원정보를 저장
 *   /rest-api/members/{id}	PUT방식으로 url들어오면 (==update)
 *   		--> 회원정보를 수정
 *   /rest-api/members/{id}	DELETE방식으로 url들어오면 (==delete)
 *   		--> 회원정보를 삭제
 *   
 * */





import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ny.mm.model.ListViewData;
import com.ny.mm.model.Member;
import com.ny.mm.model.member.JoinMember;
import com.ny.mm.model.member.JoinRestApiRequest;
import com.ny.mm.service.JoinService;
import com.ny.mm.service.MemDelService;
import com.ny.mm.service.MemListService;


@Controller
@RequestMapping("/rest-api/members")
public class RestApiController {
	
	@Autowired
	private MemListService listService;
	
	@Autowired
	private JoinService joinService;
	
	@Autowired
	private MemDelService delService;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Member> getAllList(){
		return listService.getAllList();
	}
	
	@CrossOrigin
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String joinMember(
				@RequestBody JoinRestApiRequest request,
				HttpServletRequest req
			) {
		System.out.println("---------request---------");
		System.out.println(request);
		System.out.println("---------httpServletRequest---------");
		System.out.println(req);
		
		
		int result = joinService.joinMember(req, request);
		
		return result>0 ? "success" : "fail";
	}
	
	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteMember(@PathVariable("id") String id) {
		return delService.delete(id)>0? "success" : "fail";
	}
}
