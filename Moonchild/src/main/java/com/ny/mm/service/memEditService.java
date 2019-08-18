package com.ny.mm.service;
/*-------------------
 * 파일이름: memEditService.java
 * 파일설명: 회원수정 서비스
 * 작성자: 김나연
 * 버전: 1.0.0
 * 생성일자: 2019-08-07 오후 5시 57분
 * 최종수정일자: 2019-08-13 오전 11시 30분
 * 최종수정자: 김나연
 * 최종수정내용: MyBatis를 이용 + auto mapper creating function
 * @select : 수정할 멤버 반환
 * @edit : 수정클릭시 수정 + 파일수정도 가능.
 * -------------------*/


import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ny.mm.dao.MemberDao;
import com.ny.mm.model.member.EditMember;
import com.ny.mm.model.member.Member;


@Service(value = "editService")
public class memEditService {

	/*---------------------------------------------------------
				2019-08-13에 mybatis템플릿으로 변경
	---------------------------------------------------------*/
	
	@Autowired
	private SqlSessionTemplate template;
	private MemberDao dao;
	
	//수정할 멤버 가져오기
	public Member select(String id) {
		dao = template.getMapper(MemberDao.class);
		Member member = dao.selectById(id);
		
		return member;
	}
	
	public int edit(HttpServletRequest request, EditMember edit, String oldFile) {
		dao = template.getMapper(MemberDao.class);
		int result = 0;
		
		String path = "/uploadfile/userphoto";
		String dir = request.getSession().getServletContext().getRealPath(path);
		
		Member memberinfo = edit.toMember();
		
		
		//새로 업로드한 파일이 있으면 업로드 후 기존파일 삭제함.
		if(edit.getPhoto() != null && edit.getPhoto().getSize()>0 
				&& !edit.getPhoto().isEmpty() ) {
			String newFilename = System.nanoTime() + "_" + edit.getId();
			
			try {
				edit.getPhoto().transferTo(new File(dir, newFilename));
				memberinfo.setPhoto(newFilename);
				new File(dir, oldFile).delete();
			
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			memberinfo.setPhoto(oldFile);
		}
			result = dao.updateMember(memberinfo);

			//id 수정은 안됨. id가 uk라 안됨.
//		System.out.println("service"+result);
		return result;
	}
	
	
	/**
	 * rest api 용
	 * */
	public int editRest(HttpServletRequest request, EditMember edit) {
		dao = template.getMapper(MemberDao.class);
		int result = 0;
		
		Member member = edit.toMemberRest();
		result = dao.updateMem(member);
		return result;
	}
	

	/* -------------------------------------
	 * 이전에 작성한 메서드들 @ 08-07
	 * -------------------------------------*/
//	
//	@Autowired
//	private MemberDAO dao;
//	
//	//수정할 멤버 가져오기
//	public Member select(String id) {
//		Member member = null;
//		Connection conn = null;
//		
//		try {
//			conn = ConnectionProvider.getConnection();
//			member = dao.selectById(conn, id);
//		
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return member;
//	}
//	
//	public int edit(HttpServletRequest request, EditMember edit, String oldFile) {
//	
//		int result = 0;
//		Connection conn = null;
//		
//		String path = "/uploadfile/userphoto";
//		String dir = request.getSession().getServletContext().getRealPath(path);
//		
//		Member memberinfo = edit.toMember();
//		
//		
//		//새로 업로드한 파일이 있으면 업로드 후 기존파일 삭제함.
//		if(edit.getPhoto() != null && edit.getPhoto().getSize()>0 
//				&& !edit.getPhoto().isEmpty() ) {
//			String newFilename = System.nanoTime() + "_" + edit.getId();
//			
//			try {
//				edit.getPhoto().transferTo(new File(dir, newFilename));
//				memberinfo.setPhoto(newFilename);
//				new File(dir, oldFile).delete();
//			
//			} catch (IllegalStateException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//		} else {
//			memberinfo.setPhoto(oldFile);
//		}
//		
//		
//		try {
//			
//			conn = ConnectionProvider.getConnection();
//			result = dao.updateMember(conn, memberinfo);
//
//			//id 수정은 안됨. id가 uk라 안됨.
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
////		System.out.println("service"+result);
//		return result;
//	}
}
