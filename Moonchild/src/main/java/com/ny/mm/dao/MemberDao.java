package com.ny.mm.dao;
/*-------------------
 * 파일이름: MemberStDao.java
 * 파일설명: 멤버 관련 DAO 
 * 작성자: 김나연
 * 버전: 1.0.1
 * 생성일자: 2019-08-13 오전 09시 48분
 * 최종수정일자: 2019-08-13 오전 09시 48분
 * 최종수정자: 김나연
 * 최종수정내용: MyBatis를 이용 + auto mapper creating function
 * -------------------*/

import java.util.List;
import java.util.Map;

import com.ny.mm.model.member.Member;
import com.ny.mm.model.member.SearchMember;
import com.ny.mm.service.memberService;

public interface MemberDao {
	
	public Member selectById(String id) ; //회원한명의 정보가져오기(login + update )
	public int insertMember(Member member) ; //회원가입
	public int selectTotalCount(Map<String, Object> params); //회원총인원
	public List<Member> selectList(Map<String, Object> params); //전체회원 리스트가져오기
	public int updateMember(Member member); //업데이트 수정
	public int deleteMember(int idx) ; //삭제
	
	//search
	public List<Member> selectListByName(Map<String, Object> params) ;
	public List<Member> selectListById(Map<String, Object> params) ;
	public List<Member> selectListByBoth(Map<String, Object> params);
	

	/**
	 * Rest Api를 위한 회원전체 리스트 메서드 
	 * */
	public List<Member> selectAllList();
	public int insertMem(Member member); 
	public int updateMem(Member member); //업데이트 수정
}
