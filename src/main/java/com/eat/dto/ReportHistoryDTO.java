package com.eat.dto;

import java.sql.Date;

public class ReportHistoryDTO {
	private int his_idx;
	private int report_idx;
	private String user_id;
	private String content;
	private Date done_date;
	
	public int getHis_idx() {
		return his_idx;
	}
	public void setHis_idx(int his_idx) {
		this.his_idx = his_idx;
	}
	public int getReport_idx() {
		return report_idx;
	}
	public void setReport_idx(int report_idx) {
		this.report_idx = report_idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDone_date() {
		return done_date;
	}
	public void setDone_date(Date done_date) {
		this.done_date = done_date;
	}
	
}
