package com.eat.notice;

import java.sql.Date;

public class NoticeDTO {
	private int notice_idx;
	private String user_id;
	private String subject;
	private String content;
	private Date reg_date;
	private int b_Hit;
	private boolean highlight;
	
	
	public int getNotice_idx() {
		return notice_idx;
	}
	public void setNotice_idx(int notice_idx) {
		this.notice_idx = notice_idx;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getB_Hit() {
		return b_Hit;
	}
	public void setB_Hit(int b_Hit) {
		this.b_Hit = b_Hit;
	}
	public boolean isHighlight() {
		return highlight;
	}
	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}
	
	
}
