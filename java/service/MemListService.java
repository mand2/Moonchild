package com.ny.mm.service;
/*-------------------
 * 파일이름: MemListService.java
 * 파일설명: 회원 리스트 service
 * 작성자: 김나연
 * 버전: 1.0.0
 * 생성일자: 2019-08-07 오전 9시 50분
 * 최종수정일자: 2019-08-13 오전 09시 48분
 * 최종수정자: 김나연
 * 최종수정내용: MyBatis를 이용 + auto mapper creating function
 -------------------*/

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ny.mm.dao.MemberDAO;
import com.ny.mm.dao.MemberJtDao;
import com.ny.mm.dao.MemberStDao;
import com.ny.mm.model.ListViewData;
import com.ny.mm.model.Member;
import com.ny.mm.model.SearchMember;

@Service(value = "listService")
public class MemListService {
	
	/*---------------------------------------------------------
				2019-08-13에 mybatis템플릿으로 변경
	---------------------------------------------------------*/
	
	@Autowired
	private SqlSessionTemplate template;
	private MemberStDao dao;

	final int MEM_PER_CNT = 5; //page당 나올 멤버 수
	
	
	public ListViewData getListData(int curPageNum, SearchMember searchMember) {
		ListViewData list = new ListViewData();
		dao = template.getMapper(MemberStDao.class);
			
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("search", searchMember);
		
		//현재페이지번호
		list.setCurPageNum(curPageNum);
		
		//전체 회원수
		int totalCnt = dao.selectTotalCount(params);
		
		//전체 페이지 개수
		int pageTotalCnt = 0;
		
		if( totalCnt > 0) {
			pageTotalCnt = totalCnt / MEM_PER_CNT;
			
			if( totalCnt % MEM_PER_CNT > 0 ) {
				pageTotalCnt ++;
			}
		}
		list.setPageTotalCnt(pageTotalCnt);
		
		//구간 검색을 위한 인덱스 검색해야 함.
		int index = (curPageNum-1) * MEM_PER_CNT;
		
		/* 회원 정보리스트
		 * 검색 조건이 없는 경우 selectList = 전체 회원 리스트 가져오기 
		 * name로 검색: where name like name '%?%'
		 * id로 검색: where id like '%?%'
		 * id+name로 검색: where id like '%?%' or name like '%?%' 
		 * */
			
		List<Member> memberList = null;
		
		params.put("index", index);
		params.put("perCnt", MEM_PER_CNT);
		
		memberList = dao.selectList(params);
			
		
		/*하드코딩임 + 동적쿼리 쓸 때에 이렇게 쓰면 안됨!*/
//			if(searchMember == null) {
//				memberList = dao.selectList(params);
//			
//			} else if (searchMember.getType().equals("1")) {
//				memberList = dao.selectListByName(params);
//				
//			} else if (searchMember.getType().equals("2")) {
//				params.put("keyword", searchMember.getKeyword());
//				memberList = dao.selectListById(params);
//			
//			} else if (searchMember.getType().equals("3")) {
//				params.put("keyword", searchMember.getKeyword());
//				memberList = dao.selectListByBoth(params);
//			}
		list.setList(memberList);
		

		//test
		System.out.println("======list======");
		for(Member m : memberList) {
			System.out.println(m);
		}
		
		System.out.println(totalCnt);
		
			
			//no setting
		int no = totalCnt - index;
		list.setNo(no);
		list.setTotalCnt(totalCnt);

		return list;
	}
	
	
	

	/* -------------------------------------
	 * 		08-14 RestApi 용 메서드 작성
	 * -------------------------------------*/
	
	public List<Member> getAllList(){
		
		dao = template.getMapper(MemberStDao.class);
		List<Member> list = dao.selectAllList(); //여기서는 list 자르지않고 다 가져옴
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	/* -------------------------------------
	 * 이전에 작성한 메서드들 @ 08-07
	 * -------------------------------------*/
	
	
//	@Autowired
//	private MemberDAO dao;
//	
//	final int MEM_PER_CNT = 3; //page당 나올 멤버 수
//	
//	public ListViewData getListData(int curPageNum, SearchMember searchMember) {
//		ListViewData list = new ListViewData();
//		Connection conn= null;
//		
//		
//		try {
//			conn = ConnectionProvider.getConnection();
//			
//			//현재페이지번호
//			list.setCurPageNum(curPageNum);
//			
//			//전체 회원수
//			int totalCnt = dao.selectTotalCount(conn, searchMember);
//			
//			//전체 페이지 개수
//			int pageTotalCnt = 0;
//			
//			if( totalCnt > 0) {
//				pageTotalCnt = totalCnt / MEM_PER_CNT;
//				
//				if( totalCnt % MEM_PER_CNT > 0 ) {
//					pageTotalCnt ++;
//				}
//			}
//			list.setPageTotalCnt(pageTotalCnt);
//			
//			//구간 검색을 위한 인덱스 검색해야 함.
//			int index = (curPageNum-1) * MEM_PER_CNT;
//			
//			//총멤버+리스트출력
////			list.setList(dao.selectList(conn, index, MEM_PER_CNT, searchMember));
//			
//			/* 회원 정보리스트
//			 * 검색 조건이 없는 경우 selectList = 전체 회원 리스트 가져오기 
//			 * name로 검색: where name like name '%?%'
//			 * id로 검색: where id like '%?%'
//			 * id+name로 검색: where id like '%?%' or name like '%?%' 
//			 * */
//			
//			List<Member> memberList = null;
//			if(searchMember == null) {
//				memberList = dao.selectList(conn, index, MEM_PER_CNT);
//			} else if (searchMember.getType().equals("1")) {
//				memberList = dao.selectListByName(conn, index, MEM_PER_CNT, searchMember);
//			} else if (searchMember.getType().equals("2")) {
//				memberList = dao.selectListById(conn, index, MEM_PER_CNT, searchMember);
//			} else if (searchMember.getType().equals("3")) {
//				memberList = dao.selectListByBoth(conn, index, MEM_PER_CNT, searchMember);
//			}
//			
//			list.setList(memberList);
//			
//			//no setting
//			int no = totalCnt - index;
//			list.setNo(no);
//			
//			
//			list.setTotalCnt(totalCnt);
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
//		return list;
//	}
	
}
