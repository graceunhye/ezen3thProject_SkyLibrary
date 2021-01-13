package com.skylibrary.vo;

//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@Setter
//@Getter
//@ToString
public class SearchVO {
	private int start;
	private int end;
	private String searchType; //검색옵션
	private String searchText; //검색어
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
}
