package com.ny.mm.model.member;
/*-------------------
 * 파일이름: Member.java
 * 파일설명: 멤버 객체 model 
 * 작성자: 김나연
 * 버전: 1.0.0
 * 생성일자: 2019-08-05 오후 5시 57분
 * 최종수정일자: 2019-08-05 오후 5시 57분
 * 최종수정자: 김나연
 * 최종수정내용: member manager 스프링으로 변경 
 * -------------------*/
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.ny.mm.model.Member;

public class JoinMember {
	private String id;
	private String pw;
	private String name;
	private MultipartFile photo;

	@Override
	public String toString() {
		String info ="";
		info += "<span class=\"inputBox\">아 이 디</span>" + id + "\n";
		info += "<span class=\"inputBox\">이    름</span>" + name + "\n";
//		info += "<span class=\"inputBox\">사    진</span>" + photo.getOriginalFilename() + "\n";
		
		return info;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	
	public Member toMemberInfo() {
		Member info = new Member();
		info.setId(id);
		info.setName(name);
		info.setPw(pw);
		
		return info;
	}
}
