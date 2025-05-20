package com.eat.main;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MainController {
	
	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired MainService service;
	
	// 코스 리스트 불러오기
	@GetMapping(value="/course_list/{page}")
	public Map<String, Object> course_list(@PathVariable String page){
		log.info("page = "+page);
		resp = new HashMap<String, Object>();
		resp = service.course_list(Integer.parseInt(page));
		return resp;
	}
	
	// 코스 리스트 태그 불러오기 !!!! 바디에 post_idx 한개씩 보내용
	@PostMapping(value="/course_list_tag")
	public Map<String, Object> course_list_tag(@RequestBody Map<String, String> param){
		resp = new HashMap<String, Object>();
		resp = service.course_list_tag(param.get("post_idx"));
		return resp;
	}
	
	// 코스 리스트 사진 불러오기 !!!! 바디에 detail_idx 한개씩 보내용
	@PostMapping(value="/course_list_img")
	public Map<String, Object> course_list_img(@RequestBody Map<String, String> param){
		resp = new HashMap<String, Object>();
		resp = service.course_list_img(param.get("detail_idx"));
		return resp;
	}
	
	//댓글 작성
	@PostMapping(value = "/{user_id}/comment_insert")
	public Map<String, Object> comment_insert (@PathVariable String user_id,
			@RequestBody Map<String, String> params
			/*@RequestHeader Map<String, String> header*/){
		
		log.info("user_id: " + user_id);
		log.info("params : " + params);
		/* log.info("header : {} " , header); */
		
		resp = new HashMap<String, Object>();
		/* boolean login = false; */
		/* String token = header.get("authorization"); */
		
		/*
		 * Map<String, Object> payload = JwtUtil.readToken(token); String loginId =
		 * (String) payload.get("id");
		 * 
		 * if(loginId != null && loginId.equals(params.get("id"))) {
		 */
			params.put("user_id", user_id);
			boolean success = service.comment_insert(params);
			resp.put("success", success);
			/* login = true; */
		//}
		
		// resp.put("loginYN", login);
		
		return resp;
	}
	
	//댓글 수정
	@PutMapping(value="/{user_id}/comment_update")
	public Map<String, Object> comment_update(@PathVariable String user_id,
			@RequestBody Map<String, String> params,
			@RequestHeader Map<String, String> header){
		
		log.info("user_id: " + user_id);
		log.info("params : " + params);
		/* log.info("header : {} " , header); */
		resp = new HashMap<String, Object>();
		/* boolean login = false; */
		/*
		 * String token = header.get("authorization");
		 * 
		 * Map<String, Object> payload = JwtUtil.readToken(token); String loginId =
		 * (String) payload.get("id");
		 * 
		 * if(loginId != null && loginId.equals(params.get("user_id"))) {
		 */
			params.put("user_id", user_id);
			boolean success = service.comment_update(params);
			resp.put("success", success);
			/* login = true; */
		//}
		/* resp.put("loginYN", login); */
		
		return resp;
	}
	
	//댓글 삭제
	@DeleteMapping(value="/{user_id}/comment_del")
	public Map<String, Object> comment_del(@PathVariable String user_id,
			@RequestBody Map<String, String> params, 
			@RequestHeader Map<String, String> header){
	
		log.info("params : " + params);
		/* log.info("header : {} " , header); */
		resp = new HashMap<String, Object>();
		/* boolean login = false; */
	/*	String token = header.get("authorization");
		
		Map<String, Object> payload = JwtUtil.readToken(token);
		String loginId = (String) payload.get("id");
		
		if(loginId != null && loginId.equals(params.get("user_id"))) {*/
			params.put("user_id", user_id);
			boolean success = service.comment_del(params);
			resp.put("success", success);
			/* login = true; */
			/*
			 * } resp.put("loginYN", login);
			 */
		
		return resp;
	}
}
