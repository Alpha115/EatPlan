package com.eat.tags;

public class TagDTO {
	
	// tag 영역
	private int tag_idx;
	private String isClass;
	private String tag_name;
	private int cate_idx; // tag_cate FK
	
	public int getTag_idx() {
		return tag_idx;
	}
	public void setTag_idx(int tag_idx) {
		this.tag_idx = tag_idx;
	}
	public String getIsClass() {
		return isClass;
	}
	public void setIsClass(String isCourse) {
		this.isClass = isCourse;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public int getCate_idx() {
		return cate_idx;
	}
	public void setCate_idx(int cate_idx) {
		this.cate_idx = cate_idx;
	}
	
	
	
}
