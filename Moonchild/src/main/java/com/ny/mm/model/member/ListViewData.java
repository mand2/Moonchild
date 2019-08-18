package com.ny.mm.model.member;
/*-------------------
 * 파일이름: ListViewData.java
 * 파일설명: 회원 리스트 객체
 * 작성자: 김나연
 * 버전: 1.0.0
 * 생성일자: 2019-08-07 오전 9시 50분
 * 최종수정일자: 2019-08-07 오전 9시 50분
 * 최종수정자: 김나연
 * 최종수정내용: member manager 스프링으로 변경 
 -------------------*/




import java.util.List;
import org.springframework.stereotype.Repository;

public class ListViewData {
	
	private List<Member> list;
	private int totalCnt ; //전체 회원의 수
	private int no; //연번
	private int curPageNum; //현재페이지
	private int pageTotalCnt; //페이지 전체 수 ==paging처리위해

	public List<Member> getList() {
		return list;
	}

	public void setList(List<Member> list) {
		this.list = list;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCurPageNum() {
		return curPageNum;
	}

	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
	}

	public int getPageTotalCnt() {
		return pageTotalCnt;
	}

	public void setPageTotalCnt(int pageTotalCnt) {
		this.pageTotalCnt = pageTotalCnt;
	}
	
}
