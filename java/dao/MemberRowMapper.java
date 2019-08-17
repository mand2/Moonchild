package com.ny.mm.dao;
/*-------------------
 * 파일이름: MemberJtDao.java
 * 파일설명: 멤버 관련 raomapper 
 * 작성자: 김나연
 * 버전: 1.0.4
 * 생성일자: 2019-08-09 오전 10시 07분
 * 최종수정일자: 2019-08-09 오전 10시 07분
 * 최종수정자: 김나연
 * 최종수정내용: jdbc template을 이용
 * -------------------*/




import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ny.mm.model.Member;

public class MemberRowMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Member member = new Member();
		
		member.setIdx(rs.getInt(1));
		member.setId(rs.getString(2));
		member.setPw(rs.getString(3));
		member.setName(rs.getString(4));
		member.setPhoto(rs.getString(5));
		member.setRegDate(rs.getTimestamp(6));
		
		return member;
	}

}
