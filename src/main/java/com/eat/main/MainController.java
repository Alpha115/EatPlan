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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public Map<String, Object> course_list(@PathVariable int page) {
		log.info("page = " + page);
		resp = new HashMap<String, Object>();
		resp = service.course_list(page);
		return resp;
	}
	
	// 코스 리스트 닉네임 불러오기 !!!! 바디에 user_id 한개씩 보내용
	@PostMapping(value="/course_list_nick")
	public Map<String, Object> course_list_nick(@RequestBody Map<String, String> param){
		resp = new HashMap<String, Object>();
		resp = service.course_list_nick(param.get("user_id"));
		return resp;
	}
	
	// 코스 리스트 댓글 수 불러오기 !!!! 바디에 post_idx 한개씩 보내용
	@PostMapping(value="/course_list_cmtcnt")
	public Map<String, Object> course_list_cmtcnt(@RequestBody Map<String, String> param){
		resp = new HashMap<String, Object>();
		resp = service.course_list_cmtcnt(param.get("post_idx"));
		return resp;
	}
	
	// 코스 리스트 좋아요 수 불러오기 !!!! 바디에 post_idx 한개씩 보내용
	@PostMapping(value="/course_list_likecnt")
	public Map<String, Object> course_list_likecnt(@RequestBody Map<String, String> param){
		resp = new HashMap<String, Object>();
		resp = service.course_list_likecnt(param.get("post_idx"));
		return resp;
	}
	
	// 코스 리스트 별점 평균 불러오기 !!!! 바디에 post_idx 한개씩 보내용
	@PostMapping(value="/course_list_staravg")
	public Map<String, Object> course_list_staravg(@RequestBody Map<String, String> param){
		resp = new HashMap<String, Object>();
		resp = service.course_list_staravg(param.get("post_idx"));
		return resp;
	}
	
	// 코스 리스트 일반 태그 불러오기 !!!! 바디에 post_idx 한개씩 보내용
	@PostMapping(value="/course_list_tag")
	public Map<String, Object> course_list_tag(@RequestBody Map<String, String> param){
		resp = new HashMap<String, Object>();
		resp = service.course_list_tag(param.get("post_idx"));
		return resp;
	}
	
	// 코스 리스트 지역 태그 불러오기 !!!! 바디에 post_idx 한개씩 보내용
	@PostMapping(value="/course_list_tagarea")
	public Map<String, Object> course_list_tagarea(@RequestBody Map<String, String> param){
		resp = new HashMap<String, Object>();
		resp = service.course_list_tagarea(param.get("post_idx"));
		return resp;
	}
	
	// 코스 리스트 사진 불러오기 !!!! 바디에 detail_idx 한개씩 보내용
	@PostMapping(value = "/course_list_img")
	public Map<String, Object> course_list_img(@RequestBody Map<String, String> param) {
		resp = new HashMap<String, Object>();
		resp = service.course_list_img(param.get("detail_idx"));
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
