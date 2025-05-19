package com.eat.tags;

public class TagAreaDTO {
	private int area_tag_idx;
	private String city;
	private String dist;
	private String tag_name;
	private int cate_idx; // tag_cate FK
	
	public int getArea_tag_idx() {
		return area_tag_idx;
	}
	public void setArea_tag_idx(int area_tag_idx) {
		this.area_tag_idx = area_tag_idx;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public int getCate_idx() {
		return cate_idx;
	}
	public void setCate_idx(int cate_idx) {
		this.cate_idx = cate_idx;
	}
	
	
}
