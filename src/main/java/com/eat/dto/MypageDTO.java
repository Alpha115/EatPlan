package com.eat.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MypageDTO {

	// member
	private String user_id;
	private String pass;
	private String email;
	private String nickname;
	private String bio;
	private String location;
	private boolean admin;
	private boolean withdraw;
	private MultipartFile[] files;
	

	private List<TagPreferDTO> tagList;

	// photo
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

	public boolean isWithdraw() {
		return withdraw;
	}

	public void setWithdraw(boolean withdraw) {
		this.withdraw = withdraw;
	}

	public List<TagPreferDTO> getTagList() {
		return tagList;
	}

	public void setTagList(List<TagPreferDTO> tagList) {
		this.tagList = tagList;
	}

	public int getImg_idx() {
		return img_idx;
	}

	public void setImg_idx(int img_idx) {
		this.img_idx = img_idx;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	
	
}

	
