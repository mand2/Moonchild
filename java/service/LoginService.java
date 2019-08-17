package com.ny.mm.service;
/* 2019-08-06
 * 로그인 서비스 제작
 * ----
 * 2019-08-09
 * jdbc template로 고침
 * */


import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ny.mm.dao.MemberDAO;
import com.ny.mm.dao.MemberJtDao;
import com.ny.mm.dao.MemberStDao;
import com.ny.mm.model.Member;

@Service(value = "loginService")
public class LoginService implements memberService{

	/*---------------------------------------------------------
				2019-08-13에 mybatis템플릿으로 변경
	---------------------------------------------------------*/
	
	@Autowired
	private SqlSessionTemplate template;
	private MemberStDao dao;
	
	public boolean login(String id, String pw, HttpServletRequest request) {
		dao = template.getMapper(MemberStDao.class);
		boolean loginChk = false;
		Member member = null;
		
		member = dao.selectById(id);
			
		if(member != null && member.matchPassword(pw)) {
			//세션저장 + loginChk값 변경
			loginChk = true;
			request.getSession(true).setAttribute("loginInfo", member.toLoginInfo());
		}
		
		return loginChk;
	}



/* 2019-08-06

	@Autowired
	private MemberDAO dao;
	
	public boolean login(String id, String pw, HttpServletRequest request) {
		
		boolean loginChk = false;
		Connection conn = null;
		Member member = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			member = dao.selectOneMem(conn, id);
			
			if(member != null && member.matchPassword(pw)) {
				//세션저장 + loginChk값 변경
				loginChk = true;
				request.getSession(true).setAttribute("loginInfo", member.toLoginInfo());
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loginChk;
	}
*/

}
