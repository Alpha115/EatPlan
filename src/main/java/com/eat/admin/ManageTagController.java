package com.eat.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		resp=new HashMap<String, Object>();
		boolean success=service.addTag(tag);
		resp.put("success", success);
		return resp;
	}
	
	// ----------일반태그중복확인------------//
	@PostMapping("/overlayTag")
	public Map<String, Object> overlayTag(@RequestBody Map<String, String> param){
		resp=new HashMap<String, Object>();
		boolean usable=service.overlayTag(param.get("isClass"), param.get("tag_name"));
		resp.put("usable", usable);
		return resp;
	}
	
	// -----------일반태그삭제, idx or tag_name으로 받기---------------//
	@GetMapping("/delTag/{isClass}/{tag_idx}")
	public Map<String, Object> deleteTag(@PathVariable String isClass, @PathVariable String tag_name){
		resp=new HashMap<String, Object>();
		boolean success=service.deleteTag(isClass, tag_name);
		resp.put("success", success);
		return resp;
	}
	
	
	// -------------지역 태그 추가 ------------------//
	
	
	
}




// ------------ 일반 태그 ------------- //
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