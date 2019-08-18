package com.ny.mm.model.member;

import org.springframework.web.multipart.MultipartFile;

public class JoinRestApiRequest {
	private String id;
	private String pw;
	private String name;
	
	
	public Member toMemberInfo() {
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		return member;
	}
	

	@Override
	public String toString() {
		return "JoinRestApiRequest [id=" + id + ", pw=" + pw + ", name=" + name + "]";
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
	
	
}
