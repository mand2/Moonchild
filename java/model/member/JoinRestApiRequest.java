package com.ny.mm.model.member;

import org.springframework.web.multipart.MultipartFile;

import com.ny.mm.model.Member;

public class JoinRestApiRequest {
	private String id;
	private String pw;
	private String name;
	private MultipartFile photo;
	
	
	public Member toMemberInfo() {
		Member member = new Member();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		return member;
	}
	

	@Override
	public String toString() {
		return "JoinRestApiRequest [id=" + id + ", pw=" + pw + ", name=" + name + ", photo=" + photo + "]";
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
	
	
}
