package com.eat.member;

import java.sql.Date;

//CREATE TABLE member(
//	    user_id VARCHAR(40) PRIMARY KEY
//	    ,pass VARCHAR(40)
//	    ,email VARCHAR(100)
//	    ,nickname VARCHAR(40)
//	    ,bio VARCHAR(1000)
//	    ,location VARCHAR(20)
//	    ,admin BOOLEAN
//	    ,reg_date DATETIME DEFAULT NOW()
//	    ,img_idx INT(7)
//	    ,FOREIGN KEY(img_idx) REFERENCES photo(img_idx) ON DELETE CASCADE
//	    ,withdraw BOOLEAN default false
//	);

public class MemberDTO {
	
	private String user_id;
	private String pass;
	private String email;
	private String nickname;
	private String bio;
	private String location;
	private boolean admin;
	private Date reg_date;
	private int img_idx;
	
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
	public int getImg_idx() {
		return img_idx;
	}
	public void setImg_idx(int img_idx) {
		this.img_idx = img_idx;
	}
	
}
