package com.eat.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class ReportDTO {

	private int report_idx;
	private String reporter_id;
	private String reporter_nickname;
	private Integer img_idx;
	private String suspect_id;
	private String suspect_nickname;
	private String subject;
	private String content;
	private int reported_idx;
	private String isClass;
	private Timestamp report_date;
	private boolean done;
	private boolean isPublic;
	private MultipartFile[] files;
	
	public String getReporter_nickname() {
		return reporter_nickname;
	}
	public void setReporter_nickname(String reporter_nickname) {
		this.reporter_nickname = reporter_nickname;
	}
	public String getSuspect_nickname() {
		return suspect_nickname;
	}
	public void setSuspect_nickname(String suspect_nickname) {
		this.suspect_nickname = suspect_nickname;
	}
	
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public int getReport_idx() {
		return report_idx;
	}
	public void setReport_idx(int report_idx) {
		this.report_idx = report_idx;
	}
	public String getReporter_id() {
		return reporter_id;
	}
	public void setReporter_id(String reporter_id) {
		this.reporter_id = reporter_id;
	}
	
	public Integer getImg_idx() {
		return img_idx;
	}
	public void setImg_idx(Integer img_idx) {
		this.img_idx = img_idx;
	}

	public String getSuspect_id() {
		return suspect_id;
	}
	public void setSuspect_id(String suspect_id) {
		this.suspect_id = suspect_id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReported_idx() {
		return reported_idx;
	}
	public void setReported_idx(int reported_idx) {
		this.reported_idx = reported_idx;
	}
	public String getIsClass() {
		return isClass;
	}
	public void setIsClass(String isClass) {
		this.isClass = isClass;
	}
	public Timestamp getReport_date() {
		return report_date;
	}
	public void setReport_date(Timestamp report_date) {
		this.report_date = report_date;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
}
