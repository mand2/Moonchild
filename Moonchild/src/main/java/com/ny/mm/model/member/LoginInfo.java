package com.ny.mm.model.member;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class LoginInfo {
	private String id;
	private String name;
	private String photo;
	private Date regDate;
	private String phone;
	
	public LoginInfo(String id, String name, String photo, Date regDate, String phone) {
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.regDate = regDate;
		this.phone = phone;
	}


	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhoto() {
		return photo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public String getPhone() {
		return phone;
	}


	@Override
	public String toString() {
		return "LoginInfo [id=" + id + ", name=" + name + ", photo=" + photo + ", regDate=" + regDate + ", phone="
				+ phone + "]";
	}
	
}
