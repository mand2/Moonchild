package com.ny.mm.service;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/* 2019-08-06
 * 멤버 가입
 * 
 * */
import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ny.mm.dao.MemberDao;
import com.ny.mm.model.member.JoinMember;
import com.ny.mm.model.member.JoinRestApiRequest;
import com.ny.mm.model.member.Member;

@Service(value = "joinService")
public class JoinService implements memberService{
	
	/*---------------------------------------------------------
				2019-08-13에 mybatis템플릿으로 변경
	---------------------------------------------------------*/
	
	@Autowired
	private SqlSessionTemplate template;
	private MemberDao dao;
	
	//가입 서비스
	public int joinRestMember( HttpServletRequest request,
							JoinRestApiRequest joinMember
			) {
		
		dao = template.getMapper(MemberDao.class);
		int result = 0;
		Member memberinfo = joinMember.toMemberInfo();
		try {
			result = dao.insertMember(memberinfo);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
		
		return result;
	}
	
	//가입 서비스
	public int joinMember( HttpServletRequest request,
							JoinMember joinMember 
			) {
		
		dao = template.getMapper(MemberDao.class);
		
		int result = 0;
		
		//서버경로
		String path = "/uploadfile/userphoto"; //resource mapping해야함.
		//절대경로
		String dir = request.getSession().getServletContext().getRealPath(path);
		
		Member memberinfo = joinMember.toMemberInfo();
		
		
		//새로운 파일 이름 생성
		String newFileName = ""; 
		
		try {
			//파일 안 올렸을 때
			if(joinMember.getPhoto() != null) {
				//file을 서버의 지정 경로에 저장.
				newFileName = System.nanoTime() + "_" + joinMember.getId();
				joinMember.getPhoto().transferTo(new File(dir, newFileName));
				
				//데이터베이스 저장을 하기위한 파일이름 세팅
				memberinfo.setPhoto(newFileName);
			}
			result = dao.insertMember(memberinfo);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("오류");
			if(joinMember.getPhoto()!= null) {
				new File(dir,newFileName).delete();
			}
		}
		
		return result;
	}
	
	/*jsp로 보내지 않고 바로 연결하기*/
	public String idCheck2(String id) {
		dao = template.getMapper(MemberDao.class);
		String chk = dao.selectById(id)==null?"Y":"N";
		return chk;
	}
	
	
}
