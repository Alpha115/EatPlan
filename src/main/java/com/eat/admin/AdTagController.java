package com.eat.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagCateDTO;
import com.eat.tags.TagDTO;
import com.eat.tags.TagRequestDTO;

// 식당과 코스 태그를 관리하는 컨트롤러입니다. 모든 기능에는 관리자 권한 토큰이 필요합니다.
@CrossOrigin
@RestController
public class AdTagController {
	
	@Autowired AdTagService service;
	Map<String, Object> resp = null;
	
	// (관리자 페이지)식당의 리스트를 불러와 페이징처리하는 함수입니다. 
	@GetMapping("/adtag_restaList/{page}")
	public Map<String, Object> restaList(@PathVariable String page){
		resp=new HashMap<String, Object>();
		Map<String, Object> restaList = service.restaList(page);
		resp.put("restaList", restaList);
		return resp;
	}
	
	// (관리자 페이지)식당에 태그를 추가하는 함수입니다. 지역 태그는 포함되지 않습니다. 
	// 식당에 포함될 수 있는 식당 태그에 제한은 없습니다. 
	// 특정 식당을 식별하는 resta_idx는 요청에 따라 고정적으로 들어옵니다.
	// 그런데 tag_idx를 못받네? 걍 Map<tag_name, resta_idx> 로 받습니다
	@PostMapping("/adtag_restaTag")
	public Map<String, Object> restaTag(@RequestBody ArrayList<Map<String, Integer>> tags){
		resp=new HashMap<String, Object>();
		boolean success=service.restaTag(tags);
		resp.put("success", success);
		return resp;
	}
	// 위에꺼 만들어졌니...? 식당에 지역태그 추가해야하지 않겠니?
	// 코스 태그 추가 해야되지 않겠니...?
	
	// 태그 카테고리 추가
	@PostMapping("/adtag_cate")
	public Map<String, Object> adtag_cate(
			@RequestBody TagCateDTO dto){
		
		resp = new HashMap<String, Object>();
		
		boolean success = service.adtag_cate(dto);
		
		resp.put("success", success);
		
		return resp;
	}
	
	// 태그 카테고리 삭제
	@DeleteMapping("/adtag_cate_del")
	public Map<String, Object> adtag_cate_del(
			@RequestBody TagCateDTO dto){
		
		resp = new HashMap<String, Object>();
		
		boolean success = service.adtag_cate_del(dto);
		
		resp.put("success", success);
		
		return resp;
	}
	
	// 태그 추가
	@PostMapping("/adtag_write")
	public Map<String, Object> adtag_write(
			@RequestBody TagRequestDTO req){
		
		resp = new HashMap<String, Object>();
		TagCateDTO cate_name = req.getCate_name();
		TagAreaDTO tag_area = req.getTag_area();
		TagDTO tag = req.getTag();
		
		boolean success = service.adtag_write(cate_name, tag_area, tag);
		
		resp.put("success", success);
		
		return resp;
	}

	// 태그 삭제
	@DeleteMapping("/adtag_del")
	public Map<String, Object> adtag_del(
			@RequestBody Map<String, Integer> params){
		
		resp= new HashMap<String, Object>();
		
		boolean success = service.adtag_del(params);
		
		resp.put("success", success);
		
		return resp;
	}
	
}
