package com.ny.mm.model.member;
/*-------------------
 * 파일이름: EditMember.java
 * 파일설명: 회원수정 객체
 * 작성자: 김나연
 * 버전: 1.0.0
 * 생성일자: 2019-08-07 오후 7시 30분
 * 최종수정일자: 2019-08-07 오후 7시 30분
 * 최종수정자: 김나연
 * 최종수정내용: member manager 스프링으로 변경 
 * 수정시 기본키인 idx로 가져와야 하므로 클래스 따로 생성. 
 * -------------------*/




import org.springframework.web.multipart.MultipartFile;
import com.ny.mm.model.Member;

public class EditMember {
	
	private int idx;
	private String id;
	private String pw;
	private String name;
	private MultipartFile photo;
	private String oldFile;
	
	
	@Override
	public String toString() {
		return "EditMember [idx=" + idx + ", id=" + id + ", pw=" + pw + ", name=" + name + ", photo=" + photo
				+ ", oldFile=" + oldFile + "]";
	}

	public Member toMember() {
		Member member = new Member();
		member.setIdx(idx);
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		
		return member;
	}
	

	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public String getOldFile() {
		return oldFile;
	}
	public void setOldFile(String oldFile) {
		this.oldFile = oldFile;
	}
	
}
