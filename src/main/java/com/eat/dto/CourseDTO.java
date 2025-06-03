package com.eat.dto;

import java.sql.Timestamp;
import java.util.List;

import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagDTO;

// post 테이블 DTO
public class CourseDTO {

	private int post_idx;
	private String user_id;
	private String subject;
	private int b_hit;
	private Timestamp reg_date;
	private String post_cmt;
	private boolean isPublic;
	private boolean blind;
	private boolean tmp;
	
	

	private String nickname;
	private String thumbnail;
	
	private List<LikedDTO> likeList;
	private List<StarDTO> starList;
	private List<PhotoDTO> photos;
	
	
	

	private int total_like_count; // 좋아요 개수
	private int total_comment_count; // 댓글 수
	private double star_average; // 별점 평균
	
	public List<LikedDTO> getLikeList() {
		return likeList;
	}

	public void setLikeList(List<LikedDTO> likeList) {
		this.likeList = likeList;
	}
	
	public List<StarDTO> getStarList() {
		return starList;
	}
	public void setStarList(List<StarDTO> starList) {
		this.starList = starList;
	}
	
	 public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
	
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public List<PhotoDTO> getPhotos() {
		return photos;
	}
	
	
	public void setPhotos(List<PhotoDTO> photos) {
		this.photos = photos;
	}

	public int getTotal_comment_count() {
		return total_comment_count;
	}

	public void setTotal_comment_count(int total_comment_count) {
		this.total_comment_count = total_comment_count;
	}

	public double getStar_average() {
		return star_average;
	}

	public void setStar_average(double star_average) {
		this.star_average = star_average;
	}

	

	public int getTotal_like_count() {
		return total_like_count;
	}

	public void setTotal_like_count(int total_like_count) {
		this.total_like_count = total_like_count;
	}

	private List<TagDTO> tag_name;
	private List<TagAreaDTO> tag_name_area;
	private List<CourseTagDTO> tags; // 태그 정보

	public List<TagDTO> getTag_name() {
		return tag_name;
	}

	public void setTag_name(List<TagDTO> tag_name) {
		this.tag_name = tag_name;
	}

	public List<TagAreaDTO> getTag_name_area() {
		return tag_name_area;
	}

	public void setTag_name_area(List<TagAreaDTO> tag_name_area) {
		this.tag_name_area = tag_name_area;
	}

	public List<CourseTagDTO> getTags() {
		return tags;
	}

	public void setTags(List<CourseTagDTO> tags) {
		this.tags = tags;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getPost_cmt() {
		return post_cmt;
	}

	public void setPost_cmt(String post_cmt) {
		this.post_cmt = post_cmt;
	}

	public int getPost_idx() {
		return post_idx;
	}

	public void setPost_idx(int post_idx) {
		this.post_idx = post_idx;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getB_hit() {
		return b_hit;
	}

	public void setB_hit(int b_hit) {
		this.b_hit = b_hit;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public boolean isBlind() {
		return blind;
	}

	public void setBlind(boolean blind) {
		this.blind = blind;
	}

	public boolean isTmp() {
		return tmp;
	}

	public void setTmp(boolean tmp) {
		this.tmp = tmp;
	}

}
