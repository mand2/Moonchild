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
 *   /rest-api/members/{idx}	PUT방식으로 url들어오면 (==update)
 *   		--> 회원정보를 수정
 *   /rest-api/members/{idx}	DELETE방식으로 url들어오면 (==delete)
 *   		--> 회원정보를 삭제
 *   
 * */





import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ny.mm.model.member.EditMember;
import com.ny.mm.model.member.JoinRestApiRequest;
import com.ny.mm.model.member.Member;
import com.ny.mm.service.JoinService;
import com.ny.mm.service.MemDelService;
import com.ny.mm.service.MemListService;
import com.ny.mm.service.memEditService;


@RestController
@RequestMapping("/rest/members")
public class RestFulApiController {
	
	@Autowired
	private MemListService listService;
	
	@Autowired
	private JoinService joinService;
	
	@Autowired
	private MemDelService delService;
	
	@Autowired
	private memEditService editService;
	
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Member>> getAllList(){
		return new ResponseEntity<List<Member>>(listService.getAllList(), HttpStatus.OK);
	}
	
	//회원가입
	@CrossOrigin
	@PostMapping
	public ResponseEntity<String> joinMember(
				@RequestBody JoinRestApiRequest request,
				HttpServletRequest req
			) {
		System.out.println("---------request---------");
		System.out.println(request);
		System.out.println("---------httpServletRequest---------");
		System.out.println(req);
		
		
		int result = joinService.joinRestMember(req, request);
		
		return new ResponseEntity<String>(result > 0 ? "success" : "fail" , HttpStatus.OK)
;
	}
	
	//회원삭제
	@CrossOrigin
	@DeleteMapping(value = "/{idx}")
	public ResponseEntity<String> deleteMember(@PathVariable("idx") int idx) {
		return new ResponseEntity<String>(delService.delete(idx)>0? "success" : "fail", HttpStatus.OK);
	}
	
	//수정:회원정보가져오기
	@CrossOrigin
	@GetMapping(value = "/{id}")
	public ResponseEntity<Member> geteditMember(@PathVariable("id") String id) {
		return new ResponseEntity<Member>(editService.select(id), HttpStatus.OK);
	}
	
	//회원수정
	@CrossOrigin
	@PutMapping
	public ResponseEntity<String> editMember(@RequestBody EditMember edit) {
		System.out.println("edit :" + edit );
		return new ResponseEntity<String>(editService.editRest(edit)>0 ? "success" : "fail", HttpStatus.OK);
	}
}
