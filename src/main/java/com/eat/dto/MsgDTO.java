package com.eat.dto;

import java.sql.Timestamp;

public class MsgDTO {

	private int msg_idx;
	private String content;
	private Timestamp msg_date;
	private String recip;
	private String sender;
	private boolean recip_del;
	private boolean sender_del;
	private String subject;
	private String user_id;
	
	private String senderNickname;
	private String recipNickname;
	
	
	public String getSenderNickname() {
		return senderNickname;
	}
	public void setSenderNickname(String senderNickname) {
		this.senderNickname = senderNickname;
	}
	public String getRecipNickname() {
		return recipNickname;
	}
	public void setRecipNickname(String recipNickname) {
		this.recipNickname = recipNickname;
	}
	public int getMsg_idx() {
		return msg_idx;
	}
	public void setMsg_idx(int msg_idx) {
		this.msg_idx = msg_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(Timestamp msg_date) {
		this.msg_date = msg_date;
	}
	public String getRecip() {
		return recip;
	}
	public void setRecip(String recip) {
		this.recip = recip;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public boolean isRecip_del() {
		return recip_del;
	}
	public void setRecip_del(boolean recip_del) {
		this.recip_del = recip_del;
	}
	public boolean isSender_del() {
		return sender_del;
	}
	public void setSender_del(boolean sender_del) {
		this.sender_del = sender_del;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}
