package com.ny.mm.dao;
/*-------------------
 * 파일이름: MemberJtDao.java
 * 파일설명: 멤버 관련 DAO 
 * 작성자: 김나연
 * 버전: 1.0.4
 * 생성일자: 2019-08-09 오전 10시 07분
 * 최종수정일자: 2019-08-09 오전 10시 07분
 * 최종수정자: 김나연
 * 최종수정내용: jdbc template을 이용한 dao작성
 * -------------------*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ny.mm.model.Member;
import com.ny.mm.model.SearchMember;

@Repository("templatedao")
public class MemberJtDao {
	
	@Autowired
	private JdbcTemplate template;

	
	//로그인 member의 존재여부와 id pw가져올수있게,,,
	public Member selectOneMem(String id) {

		String sql = "select * from project_01.memberinfo "
					+ " where id = ?";
		
		List<Member> list = 
					template.query(sql, new Object[] {id}, new MemberRowMapper());
	
		
		return list.isEmpty() ? null : list.get(0);
	}
	
	public int insertMember(Member member) {

		String sql = "insert into project_01.memberinfo (ID, PW, NAME, PHOTO) values (?, ?, ?, ?)";
		
		return template.update(sql, member.getId(), member.getPw(), member.getName(), member.getPhoto());
	}
	
	//회원 총 인원
	public int selectTotalCount(SearchMember searchMember) {

		String sql = "select count(*) from project_01.memberinfo ";
		
		String opt = "";
		String condition = "";
		
		if(searchMember != null) {
			opt = searchMember.getType();
			condition = "'%" + searchMember.getKeyword() + "%'";
			
			if(opt.equals("1")) {
				sql += " where name like " + condition;
			}
			if(opt.equals("2")) {
				sql += " where id like " + condition;
			}
			if(opt.equals("3")) {
				sql += " where id like " +condition + " or ";
				sql += " name like " + condition;
			}
		}
		
		return template.queryForObject(sql, Integer.class);
	}
	

	//전체회원 리스트가져오기
	public List<Member> selectList(int index, int perCnt) {
		String sql = "select * from project_01.memberinfo limit ?, ?";
		
		return template.query(sql, new MemberRowMapper(), index, perCnt);
	}
	
	

	/*유저이름 검색*/
	public List<Member> selectListByName(int index, int perCnt, SearchMember searchMember) {

		String sql = "select * from project_01.memberinfo ";
		sql += " where name like ? ";
		sql += " limit ?, ? ";

		String condition = "%" + searchMember.getKeyword() + "%";
		
		return template.query(sql, new MemberRowMapper(), condition, index, perCnt);
	}
		
	

	/*유저ID 검색*/
	public List<Member> selectListById(int index, int perCnt, SearchMember searchMember) {
		String sql = "select * from project_01.memberinfo ";
		sql += " where id like ? ";
		sql += " limit ?, ? ";
		
		String condition = "%" + searchMember.getKeyword() + "%";
		
		//rowmapper은 행 하나 나오게
		return template.query(sql, new MemberRowMapper(), condition, index, perCnt);
	}
	

	/*유저ID+이름 검색*/
	public List<Member> selectListByBoth(int index, int perCnt, SearchMember searchMember) {
		String sql = "select * from project_01.memberinfo ";
		sql += " where id like ? ";
		sql += " or name like ? ";
		sql += " limit ?, ? ";
		
		String condition = "%" + searchMember.getKeyword() + "%";
		
		return template.query(sql, new MemberRowMapper(), condition, condition, index, perCnt);
	}
		
	//수정: 수정할 멤버 정보 가져오기
	public Member selectById(String id) {	
		String sql = "select * from project_01.memberinfo "
				+ " where id = ?";
		
		Member member = null;
		
		try {
			member = template.queryForObject(sql, new MemberRowMapper(), id); 
		} catch(DataAccessException e) {
			e.printStackTrace();
		}
				
		return member;
	}
	

	//업데이트수정: 회원목록에서
	public int updateMember(Member member) {
		String sql = "update project_01.memberinfo "
				+ " set name = ? ,"
				+ " pw = ? ,"
				+ " photo = ? "
				+ " where idx = ?" ;
		return template.update(sql, member.getName(), member.getPw(),member.getPhoto(),member.getIdx());
	}
	
	//삭제: 회원목록에서
	public int deleteMember(String id) {
		
		String sql = "delete from project_01.memberinfo "
					+ " where id = ?";
		return template.update(sql, id);
	}

	
	
	/* -------------------------------------
	 * 이전에 작성한 메서드들 @ 08-07
	 * -------------------------------------*/
	
//	
//	//VO객체를 이용하여 : JDBC insert query 작성, 실행
//	public int insertMember(Connection conn, Member vo) {
//		int result = 0;
//		
//		PreparedStatement pstmt = null;
//		
//		String sql = "insert into project_01.memberinfo (ID, PW, NAME, PHOTO) values (?, ?, ?, ?)";
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, vo.getId());
//			pstmt.setString(2, vo.getPw());
//			pstmt.setString(3, vo.getName());
//			pstmt.setString(4, vo.getPhoto());
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			System.out.println(e + " memberInsert FAIL!!");
//		} finally {
//			JdbcUtil.close(pstmt);
//		}
//		
//		return result;
//	}
//	
//	
//	//전체회원 리스트가져오기
//	public List<Member> selectList(Connection conn, int index, int perCnt) {
//		/*유저이름순정렬*/
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "select * from project_01.memberinfo limit ?, ?";
//		
//		List<Member> list = new ArrayList<Member>();
//
//		String opt = "";
//		String condition = "";
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setInt(1, index);
//			pstmt.setInt(2, perCnt);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				Date date = rs.getTimestamp(6); 
//				
//				list.add(new Member(rs.getInt(1), 
//									rs.getString(2), 
//									rs.getString(3),
//									rs.getString(4), 
//									rs.getString(5), 
//									date
//						));
//			}
//		
//		
//		} catch (SQLException e) {
//			System.out.println(e + "Show ALL member LIST FAIL!!");
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		
//		return list;
//	}
//	
//	
//	/*유저이름 검색*/
//	public List<Member> selectListByName(Connection conn, int index, int perCnt, SearchMember searchMember) {
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		List<Member> list = new ArrayList<Member>();
//		
//		String opt = "";
//		String condition = "";
//		
//		
//		try {
//				opt = searchMember.getType();
//				condition = searchMember.getKeyword();
//				
//				String sql = "select * from project_01.memberinfo ";
//				sql += " where name like ? ";
//				sql += " limit ?, ? ";
//				
//				pstmt = conn.prepareStatement(sql);
//
//				pstmt.setString(1, "%"+condition+"%");
//				pstmt.setInt(2, index);
//				pstmt.setInt(3, perCnt);
//			
//				rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				Date date = rs.getTimestamp(6); 
//				
//				list.add(new Member(rs.getInt(1), 
//						rs.getString(2), 
//						rs.getString(3),
//						rs.getString(4), 
//						rs.getString(5), 
//						date
//						));
//			}
//			
//			
//		} catch (SQLException e) {
//			System.out.println(e + "Show ALL member LIST FAIL!!");
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		
//		return list;
//	}
//	
//	/*유저ID 검색*/
//	public List<Member> selectListById(Connection conn, int index, int perCnt, SearchMember searchMember) {
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		List<Member> list = new ArrayList<Member>();
//		
//		String opt = "";
//		String condition = "";
//		
//		
//		try {
//			opt = searchMember.getType();
//			condition = searchMember.getKeyword();
//			
//			String sql = "select * from project_01.memberinfo ";
//			sql += " where id like ? ";
//			sql += " limit ?, ? ";
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, "%"+condition+"%");
//			pstmt.setInt(2, index);
//			pstmt.setInt(3, perCnt);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				Date date = rs.getTimestamp(6); 
//				
//				list.add(new Member(rs.getInt(1), 
//						rs.getString(2), 
//						rs.getString(3),
//						rs.getString(4), 
//						rs.getString(5), 
//						date
//						));
//			}
//			
//			
//		} catch (SQLException e) {
//			System.out.println(e + "Show ALL member LIST FAIL!!");
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		
//		return list;
//	}
//	
//	/*유저ID+이름 검색*/
//	public List<Member> selectListByBoth(Connection conn, int index, int perCnt, SearchMember searchMember) {
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		List<Member> list = new ArrayList<Member>();
//		
//		String opt = "";
//		String condition = "";
//		
//		
//		try {
//			opt = searchMember.getType();
//			condition = searchMember.getKeyword();
//			
//			String sql = "select * from project_01.memberinfo ";
//			sql += " where id like ? ";
//			sql += " or name like ? ";
//			sql += " limit ?, ? ";
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, "%"+condition+"%");
//			pstmt.setString(2, "%"+condition+"%");
//			pstmt.setInt(3, index);
//			pstmt.setInt(4, perCnt);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				Date date = rs.getTimestamp(6); 
//				
//				list.add(new Member(rs.getInt(1), 
//						rs.getString(2), 
//						rs.getString(3),
//						rs.getString(4), 
//						rs.getString(5), 
//						date
//						));
//			}
//			
//			
//		} catch (SQLException e) {
//			System.out.println(e + "Show ALL member LIST FAIL!!");
//		
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		
//		return list;
//	}
//	
//	//로그인-1 member의 존재여부와 id pw가져올수있게,,,
//	public Member selectOneMem(Connection conn, String id) {
//		Member member = null;
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		String sql = "select * from project_01.memberinfo "
//					+ " where id = ?";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				member = new Member();
//				
//				member.setId(rs.getString(2));
//				member.setPw(rs.getString(3));
//				member.setName(rs.getString(4));
//				member.setPhoto(rs.getString(5));
//				member.setRegDate(rs.getTimestamp(6));
//			}
//		
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		
//		return member;
//	}
//	
//	//회원 총 인원
//	public int selectTotalCount(Connection conn, SearchMember searchMember) {
//		int totalCnt = 0;
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		String sql = "select count(*) from project_01.memberinfo ";
//		
//		String opt = "";
//		String condition = "";
//		
//		if(searchMember != null) {
//			opt = searchMember.getType();
//			condition = searchMember.getKeyword();
//			
//			/* 참고:
//			 * opt == "1" 는 안됨 
//			 * searchMember가 참조변수로 들어온거라 주소값임. 문자비교인 equals 메서드 써야 함.
//			 * */
//			
//			if(opt.equals("1")) {
//				sql += " where name like ? ";
//			}
//			if(opt.equals("2")) {
//				sql += " where id like ? ";
//			}
//			if(opt.equals("3")) {
//				sql += " where id like ? or ";
//				sql += " name like ? ";
//			}
//			
//		}//서치 누를떄만 넣어줌.
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//
//			if(searchMember != null) {
//				pstmt.setString(1, "%"+condition+"%");
//				if( opt.equals("3")) {
//					pstmt.setString(2, "%"+condition+"%");
//				}
//			}
//
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				totalCnt = rs.getInt(1);
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		
//		return totalCnt;
//	}
//	
//	//삭제: 회원목록에서
//	public int deleteMember(Connection conn, String id) {
//		int result = 0;
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		String sql = "delete from project_01.memberinfo "
//					+ " where id = ?";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		
//		return result;
//	} 
//	
//	//수정: 수정할 멤버 정보 가져오기
//	public Member selectById(Connection conn, String id) {
//		Member member = null;
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		String sql = "select * from project_01.memberinfo "
//				+ " where id = ?";
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, id);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				member = new Member();
//				
//				member.setIdx(rs.getInt(1));
//				member.setId(rs.getString(2));
//				member.setPw(rs.getString(3));
//				member.setName(rs.getString(4));
//				member.setPhoto(rs.getString(5));
//				member.setRegDate(rs.getTimestamp(6));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		
//		return member;
//	}
//	
//	//업데이트수정: 회원목록에서
//	public int updateMember(Connection conn, Member member) {
//		int result = 0;
//		
//		PreparedStatement pstmt = null;
//		
//		String sql = "update project_01.memberinfo "
//					+ " set name = ? ,"
//					+ " pw = ? ,"
//					+ " photo = ? "
//					+ " where idx = ?" ;
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, member.getName());
//			pstmt.setString(2, member.getPw());
//			pstmt.setString(3, member.getPhoto());
//			pstmt.setInt(4, member.getIdx());
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	
}
