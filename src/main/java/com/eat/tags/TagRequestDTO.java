package com.eat.tags;

public class TagRequestDTO {

	private TagCateDTO cate_name;
	private TagAreaDTO tag_area;
	private TagDTO tag;
	
	public TagCateDTO getCate_name() {
		return cate_name;
	}
	public void setCate_name(TagCateDTO cate_name) {
		this.cate_name = cate_name;
	}
	public TagAreaDTO getTag_area() {
		return tag_area;
	}
	public void setTag_area(TagAreaDTO tag_area) {
		this.tag_area = tag_area;
	}
	public TagDTO getTag() {
		return tag;
	}
	public void setTag(TagDTO tag) {
		this.tag = tag;
	}
	
	
}
