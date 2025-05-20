package com.eat.regist;

import com.eat.main.CourseDTO;
import com.eat.main.CourseTagDTO;
import com.eat.main.DetailCmtDTO;
import com.eat.main.DetailRestaDTO;
import com.eat.main.TimelineDTO;

public class RegistRequestDTO {
	private CourseDTO content;
	private DetailRestaDTO content_detail;
	private DetailCmtDTO content_detailcmt;
	private CourseTagDTO tag;
	private TimelineDTO time;
	
	public DetailCmtDTO getContent_detailcmt() {
		return content_detailcmt;
	}
	public void setContent_detailcmt(DetailCmtDTO content_detailcmt) {
		this.content_detailcmt = content_detailcmt;
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
	public DetailRestaDTO getContent_detail() {
		return content_detail;
	}
	public void setContent_detail(DetailRestaDTO content_detail) {
		this.content_detail = content_detail;
	}
	public CourseTagDTO getTag() {
		return tag;
	}
	public void setTag(CourseTagDTO tag) {
		this.tag = tag;
	}
	
	
}
