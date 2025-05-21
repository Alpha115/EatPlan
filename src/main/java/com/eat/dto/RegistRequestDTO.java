package com.eat.dto;

import java.util.List;

// 코스 작성을 위한 DTO 5단합체
public class RegistRequestDTO {
	private CourseDTO content;
	private TimelineDTO time;
    private List<DetailRestaDTO> content_detail_resta;     
    private List<DetailCmtDTO> content_detail_cmt;
    private List<CourseTagDTO> tags;  
	
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
