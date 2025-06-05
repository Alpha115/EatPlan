package com.eat.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.CourseDTO;
import com.eat.dto.LikedDTO;
import com.eat.dto.MainDTO;
import com.eat.dto.RegistRequestDTO;
import com.eat.dto.StarDTO;

@CrossOrigin
@RestController
public class MainController {

	Map<String, Object> resp = null;
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	MainService service;

	// 코스 리스트 불러오기
	@GetMapping(value = "/course_list/{page}/{sort}")
	public Map<String, Object> course_list(@PathVariable int page, @PathVariable String sort) {
		resp = new HashMap<String, Object>();
		resp = service.course_list(page, sort);
		return resp;
	}
	
	// 좋아요 높은 순서대로 코스 리스트 불러오기 (주간)
	@GetMapping(value = "/weekly_best_list")
	public Map<String, Object> weekly_best_list() {
		resp = new HashMap<String, Object>();
		resp = service.weekly_best_list();
		return resp;
	}
	
	// 좋아요 높은 순서대로 코스 리스트 불러오기 (월간)
	@GetMapping(value="/monthly_best_list")
	public Map<String, Object> monthly_best_list() {
		resp = new HashMap<String, Object>();
		resp = service.monthly_best_list();
		return resp;
	}

	// 코스 상세보기
	@GetMapping(value = "/courseDetail")
	public Map<String, Object> courseDetail(@RequestParam int post_idx,
			@RequestParam(defaultValue = "tag") String isClass) {

		resp = new HashMap<String, Object>();

		RegistRequestDTO dto = service.courseDetail(post_idx, isClass);
		resp.put("detail", dto); // detail로 수정

		return resp;
	}

	// 코스검색
	@GetMapping(value = "/search_course")
	public ResponseEntity<List<CourseDTO>> search_course(
			@RequestParam(value = "subject", required = false) String subject,
			@RequestParam(value = "nickname", required = false) String nickname,
			@RequestParam(value = "tag", required = false) String tag) {

		List<CourseDTO> entity = null;
		log.info("받아온 제목 : "+subject+" 받아온 아이디 : "+nickname+" 받아온 태그 : "+tag);

		entity = service.search_course(subject, nickname, tag);
		return ResponseEntity.ok(entity);

	}
	
	// --------------------사진 요청 ---------------------//
	@GetMapping("/image/{new_filename}")
	public ResponseEntity<Resource> getImage(@PathVariable String new_filename){
		return service.getFile(new_filename,"photo");
	}
	
	// ------------------ 사진요청(idx) -------------------- //
	@GetMapping("/imageIdx/{img_idx}")
	public ResponseEntity<Resource> getImageByIdx(@PathVariable int img_idx){
		return service.getFile(img_idx);
	}
	
	// 좋아요 누르기
	@PostMapping(value="/like")
	public Map<String, Object> like (
			@RequestBody LikedDTO params){
		resp = new HashMap<String, Object>();
		
		boolean success = service.like(params);
		
		resp.put("success", success);
		return resp;
	}
	
	// 좋아요 상태 확인
	@GetMapping(value="/like_check")
	public Map<String, Object> likeCheck (
			@RequestParam String user_id,
		    @RequestParam Integer post_idx) {
		resp = new HashMap<String, Object>();
		
		log.info("받아온 좋아요상태 파람 : "+user_id + post_idx);
		boolean liked = false;
		
		liked = service.likeCheck(user_id, post_idx);

		resp.put("liked", liked);
		
		return resp;
		
	}
	
	// 좋아요 상태 확인 댓글
	@PostMapping (value="/like_check_cmt")
	public Map<String, Object> likeCheckCmt (
			@RequestBody Map<String, Object> body) {
		
		resp = new HashMap<String, Object>();
		log.info("받아온 좋아요 상태 댓글 파람 : "+body);
		
	    String user_id = (String) body.get("user_id");
	    List<Integer> cmtIdxList = (List<Integer>) body.get("cmt_idx_list");
		
	    List<Map<String, Object>> likeCheckCmt = service.likeCheckCmt(user_id, cmtIdxList);
	    
	    resp.put("likeCheckCmt", likeCheckCmt);
	    
	    log.info("likeCheckCmt"+likeCheckCmt);
	    
		return resp;
	}
	
	// 별점 주기
	@PostMapping(value="/star")
	public Map<String, Object> star (
			@RequestBody StarDTO params){
		resp = new HashMap<String, Object>();
		
		boolean success = service.star(params);
		
		resp.put("success", success);
		return resp;
	}

}
