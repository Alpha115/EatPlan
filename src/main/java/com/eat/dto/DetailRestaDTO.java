package com.eat.dto;

// 식당 일정 정보를 담는 DTO
public class DetailRestaDTO {

	private int detail_idx;
	private int post_idx;
	private int resta_idx;
	private String comment;
	private String start;
	
	public int getDetail_idx() {
		return detail_idx;
	}
	public void setDetail_idx(int detail_idx) {
		this.detail_idx = detail_idx;
	}
	public int getPost_idx() {
		return post_idx;
	}
	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}
	public int getResta_idx() {
		return resta_idx;
	}
	public void setResta_idx(int resta_idx) {
		this.resta_idx = resta_idx;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	
}
