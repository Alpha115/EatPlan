package com.eat.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.CourseDTO;
import com.eat.dto.RegistRequestDTO;

@CrossOrigin
@RestController
public class MainController {

	Map<String, Object> resp = null;
	Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	MainService service;

	// 코스 리스트 불러오기
	@GetMapping(value = "/course_list/{page}")
	public Map<String, Object> course_list(
			@PathVariable int page) {
		log.info("page = " + page);
		resp = new HashMap<String, Object>();
		resp = service.course_list(page);
		return resp;
	}
	
	//코스 상세보기
	@GetMapping(value="/courseDetail")
	public Map<String, Object>courseDetail(@RequestParam int post_idx){
		
		Map<String, Object> resp = new HashMap<String, Object>();
		RegistRequestDTO dto = service.courseDetail(post_idx);
		resp.put("dto", dto);
		
		return resp;
	}
	
	//코스검색
	@GetMapping(value= "/search_course")
	public ResponseEntity<List<CourseDTO>>search_course(@RequestParam (required = false)String subject , 
			@RequestParam (required = false)String user_id,
			@RequestParam (required = false)String tag){
		
		List<CourseDTO> resp = service.search_course(subject, user_id, tag);
		
		return ResponseEntity.ok(resp);
		
	}
	
	
	
	
}
