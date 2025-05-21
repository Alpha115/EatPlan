package com.eat.regist;

// 식당 정보를 담는 데이터 타입
public class RestaurantDTO {
	private int resta_idx;
	private int img_idx;
	private int area_tag_idx;
	private String address;
	private String resta_name;
	private String url;
	private double lat;
	private double lng;
	private String resta_bio;
	
	public int getResta_idx() {
		return resta_idx;
	}
	public void setResta_idx(int resta_idx) {
		this.resta_idx = resta_idx;
	}
	public int getImg_idx() {
		return img_idx;
	}
	public void setImg_idx(int img_idx) {
		this.img_idx = img_idx;
	}
	public int getArea_tag_idx() {
		return area_tag_idx;
	}
	public void setArea_tag_idx(int area_tag_idx) {
		this.area_tag_idx = area_tag_idx;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getResta_name() {
		return resta_name;
	}
	public void setResta_name(String resta_name) {
		this.resta_name = resta_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public String getResta_bio() {
		return resta_bio;
	}
	public void setResta_bio(String resta_bio) {
		this.resta_bio = resta_bio;
	}
	
	
}
