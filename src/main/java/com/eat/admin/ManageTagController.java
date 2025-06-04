package com.eat.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ManageTagController {

	Map<String, Object> resp = null;
	@Autowired ManageTagService service;
	
	// ------------일반 태그 추가 ---------------//
	@PostMapping("/addTag")
	public Map<String, Object> addTag(@RequestBody DefaultTag tag){
//		resp=new HashMap<String, Object>();
		resp=service.addTag(tag);
		return resp;
	}
		
	// -----------일반태그삭제, tag_name으로 받기---------------//
	@PostMapping("/delTag")
	public Map<String, Object> deleteTag(@RequestBody Map<String, String> params){
		resp=new HashMap<String, Object>();
		boolean success=service.deleteTag(params.get("isClass"), params.get("tag_name"));
		resp.put("success", success);
		return resp;
	}
	
	// -------------지역 태그 추가 ------------------//
	
	
	
	
	
}




// ------------ 일반 태그 클래스------------- //
class DefaultTag{
	private int cate_idx;
	private String isClass;
	private String tag_name;
	
	public int getCate_idx() {
		return cate_idx;
	}
	public void setCate_idx(int cate_idx) {
		this.cate_idx = cate_idx;
	}
	public String getIsClass() {
		return isClass;
	}
	public void setIsClass(String isClass) {
		this.isClass = isClass;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
}