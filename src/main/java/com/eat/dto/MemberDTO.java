package com.eat.dto;

import java.sql.Date;

public class MemberDTO {
	
	private String user_id;
	private String pass;
	private String email;
	private String nickname;
	private String bio;
	private String location;
	private boolean admin;
	private Date reg_date;
	private Integer img_idx;
	private boolean withdraw;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Integer getImg_idx() {
		return img_idx;
	}
	public void setImg_idx(Integer img_idx) {
		this.img_idx = img_idx;
	}
	public boolean isWithdraw() {
		return withdraw;
	}
	public void setWithdraw(boolean withdraw) {
		this.withdraw = withdraw;
	}
	
}
