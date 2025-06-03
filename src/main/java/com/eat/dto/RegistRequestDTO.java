package com.eat.dto;

import java.util.List;

import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagDTO;

// 코스 작성, 수정, 상세보기를 위한 DTO 8단합체
public class RegistRequestDTO {
	private CourseDTO content;
	private TimelineDTO time;
	private MemberDTO nickname;
    private List<DetailRestaDTO> content_detail_resta;     
    private List<DetailCmtDTO> content_detail_cmt;
    private List<CourseTagDTO> tags;
    private List<TagDTO> tag_name;
    private List<TagAreaDTO> tag_name_area;
    private List<LikedDTO> likeList;
    private List<StarDTO> starList;
    
    
   
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
	public MemberDTO getNickname() {
		return nickname;
	}
	public void setNickname(MemberDTO nickname) {
		this.nickname = nickname;
	}
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
	public List<DetailRestaDTO> getContent_detail_resta() {
		return content_detail_resta;
	}
	public void setContent_detail_resta(List<DetailRestaDTO> content_detail_resta) {
		this.content_detail_resta = content_detail_resta;
	}
	public List<DetailCmtDTO> getContent_detail_cmt() {
		return content_detail_cmt;
	}
	public void setContent_detail_cmt(List<DetailCmtDTO> content_detail_cmt) {
		this.content_detail_cmt = content_detail_cmt;
	}
	public TimelineDTO getTime() {
		return time;
	}
	public void setTime(TimelineDTO time) {
		this.time = time;
	}
	public CourseDTO getContent() {
		return content;
	}
	public void setContent(CourseDTO content) {
		this.content = content;
	}
	public List<CourseTagDTO> getTags() {
		return tags;
	}
	public void setTags(List<CourseTagDTO> tags) {
		this.tags = tags;
	}

	
}
