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

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Member {
	private int idx;
	private String id;
	@JsonIgnore
	private String pw;
	private String name;
	private String photo;
	private Date regDate;
	private String phone;
	

	public Member() {
		this.regDate = new Date();
	}

	public Member(String id, String pw, String name, String photo) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.photo = photo;
		this.regDate = new Date();		
	}
	
	public Member(int idx, String id, String pw, String name, String photo, Date regDate) {
		this.idx = idx;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.photo = photo;
		this.regDate = regDate;
	}


	@Override
	public String toString() {
		return "Member [idx=" + idx + ", id=" + id + ", pw=" + pw + ", name=" + name + ", photo=" + photo + ", regDate="
				+ regDate + ", phone=" + phone + "]";
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	  //memberInfo 인스턴스에서 LoginInfo객체 반환하는 메서드 
	public LoginInfo toLoginInfo() {
		return new LoginInfo(id, name, photo, regDate, phone); 
	}
	 
	
	public boolean hasPassword() {
		return pw != null && !pw.isEmpty();
	}
	
	public boolean matchPassword(String pw) {
		return hasPassword() && this.pw.equals(pw);
	}
	
}
