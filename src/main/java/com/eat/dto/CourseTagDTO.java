package com.eat.dto;

// 코스 태그 테이블 DTO
public class CourseTagDTO {

	private int idx;
	private String isClass;
	private int post_idx;
	
	public String getIsClass() {
		return isClass;
	}
	public void setIsClass(String isClass) {
		this.isClass = isClass;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getPost_idx() {
		return post_idx;
	}
	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}
	
	
}
