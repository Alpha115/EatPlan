package com.eat.report;

import java.sql.Date;

public class ReportDTO {

	private int report_idx;
	private String reporter_id;
	private int img_idx;
	private String suspect_id;
	private String subject;
	private String content;
	private int reported_idx;
	private String isClass;
	private Date report_date;
	private boolean done;
	private boolean isPublic;
	
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
	public int getImg_idx() {
		return img_idx;
	}
	public void setImg_idx(int img_idx) {
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
	public Date getReport_date() {
		return report_date;
	}
	public void setReport_date(Date report_date) {
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
