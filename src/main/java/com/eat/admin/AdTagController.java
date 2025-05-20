package com.eat.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eat.tags.TagRestDTO;

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
	// 그런데 키값을 int로 못받네?
	@PostMapping("/adtag_restaTag")
	public Map<String, Object> restaTag(@RequestBody ArrayList<TagRestDTO> tags){
		resp=new HashMap<String, Object>();
		boolean success=service.restaTag(tags);
		resp.put("success", success);
		return resp;
	}
	
}
