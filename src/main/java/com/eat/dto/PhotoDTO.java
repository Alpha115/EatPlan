package com.eat.dto;

// 사진 정보를 담는 DTO
public class PhotoDTO {
	
	private int img_idx;
	private String photo_class; //class => photo_class 로 DTO한정 이름 변경했습니다
	private String new_filename;
	 private int    post_idx; 
	 
	 
	 
	public int getPost_idx() {
		return post_idx;
	}
	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}
	public int getImg_idx() {
		return img_idx;
	}
	public void setImg_idx(int img_idx) {
		this.img_idx = img_idx;
	}
	public String getPhoto_class() {
		return photo_class;
	}
	public void setPhoto_class(String photo_class) {
		this.photo_class = photo_class;
	}
	public String getNew_filename() {
		return new_filename;
	}
	public void setNew_filename(String new_filename) {
		this.new_filename = new_filename;
	}
	
	

}
