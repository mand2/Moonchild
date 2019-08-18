package com.ny.mm.model.member; 
/* 2019-08-07 
 * search
 * 
 * 
 */

public class SearchMember {
	private String keyword;
	private String type;
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "SearchMember [keyword=" + keyword + ", type=" + type + "]";
	}
	
	
	
}
