package com.eat.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// 식당과 코스 태그를 관리하는 컨트롤러입니다. 모든 기능에는 관리자 권한 토큰이 필요합니다.
@CrossOrigin
@RestController
public class AdTagController {
	
	@Autowired AdTagService service;
	Map<String, Object> resp = null;
	
	// 식당의 리스트를 불러와 페이징처리하는 함수입니다. (관리자 페이지)
	@GetMapping("/adtag_restaList/{page}")
	public Map<String, Object> restaList(@PathVariable String page){
		resp=new HashMap<String, Object>();
		Map<String, Object> restaList = service.restaList(page);
		resp.put("restaList", restaList);
		return resp;
	}
	
}
