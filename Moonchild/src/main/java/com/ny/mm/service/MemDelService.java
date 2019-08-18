package com.ny.mm.service;
/* 2019-08-07
 * member delete service
 * */

import java.sql.Connection;
import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ny.mm.dao.MemberDao;

@Service(value = "deleteService")
public class MemDelService {
	
	/*---------------------------------------------------------
				2019-08-13에 mybatis템플릿으로 변경
	---------------------------------------------------------*/
	
	@Autowired
	private SqlSessionTemplate template;
	private MemberDao dao;
	
	public int delete(int idx) {
		dao = template.getMapper(MemberDao.class);
		return dao.deleteMember(idx);
	}
	

	/* -------------------------------------
	 * 이전에 작성한 메서드들 @ 08-07
	 * -------------------------------------*/
//	@Autowired
//	private MemberDAO dao;
//	
//	public int delete(String id) {
//		int result = 0;
//		Connection conn = null;
//		
//		try {
//			conn = ConnectionProvider.getConnection();
//			
//			conn.setAutoCommit(false);
//			
//			result = dao.deleteMember(conn, id);
//			conn.commit();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	
}
