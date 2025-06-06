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

import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagRestDTO;

@CrossOrigin
@RestController
public class ManageTagController {

	Map<String, Object> resp = null;
	@Autowired ManageTagService service;
	
	// ------------일반 태그 추가 ---------------//
	@PostMapping("/addTag")
	public Map<String, Object> addTag(@RequestBody DefaultTag tag){
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
	
	// -------------- 지역태그추가(in process) -------------//
	@PostMapping("/addAreaTag")
	public Map<String, Object> addAreaTag(@RequestBody TagAreaDTO params){
		resp=new HashMap<String, Object>();
		boolean success=service.addAreaTag(params);
		resp.put("success", success);
		return resp;
	}
	
	
	// -------------지역태그삭제----------------//
	@GetMapping("/delAreaTag/{tag}")
	public Map<String, Object> deleteAreaTag(@PathVariable String tag){
		resp=new HashMap<String, Object>();
		boolean success=service.deleteAreaTag(tag);
		resp.put("success", success);
		return resp;
	}
	
	// ------------- resta_idx로 식당정보 가져오기 ------------//
	@GetMapping("/restaDetail/{resta_idx}")
	public Map<String, Object> restaDetail(@PathVariable String resta_idx){
		resp=new HashMap<String, Object>();
		
		// {"resta": "디테일 정보 dto", "tags": "tag_Resta dto"}
		resp.put("detail", service.restaDetail(resta_idx));
		resp.put("tags", service.restaTags(resta_idx));
		
		return resp;
	}
	
	// -------------식당으로부터 태그 제거(tag_idx)---------------//
	@GetMapping("/restaTagDel/{resta_idx}/{tag_idx}")
	public Map<String, Object> delRestaTag(@PathVariable String resta_idx, @PathVariable String tag_idx){
		resp=new HashMap<String, Object>();
		boolean success=service.delRestaTag(resta_idx, tag_idx);
		resp.put("success", success);
		return resp;
		
	}
	
	// ---------------------- 식당에 태그 추가 ----------------- //
	@PostMapping("/addTagtoResta")
	public Map<String, Object> addTagToResta(@RequestBody TagRestDTO tag_rest){
		resp=new HashMap<String, Object>();
		boolean success=service.addTagToResta(tag_rest);
		resp.put("success", success);
		return resp;
	}
	
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