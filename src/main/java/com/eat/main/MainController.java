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

import com.eat.utils.JwtUtil;

@CrossOrigin
@RestController
public class MainController {
	
	

	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());
	@Autowired MainService service;
	
	// 코스 리스트 불러오기 수정필요
	@GetMapping(value="/course_list/{page}")
	public Map<String, Object> course_list(@PathVariable String page){
		log.info("page = "+page);
		resp = new HashMap<String, Object>();
		resp = service.course_list(Integer.parseInt(page));
		return resp;
	}
	
	//댓글 작성
	@PostMapping(value = "/comment_insert")
	public Map<String, Object> comment_insert (@RequestBody Map<String, String> params
			/*@RequestHeader Map<String, String> header*/){
		
		log.info("params : " + params);
		/* log.info("header : {} " , header); */
		resp = new HashMap<String, Object>();
		boolean login = false;
		/* String token = header.get("authorization"); */
		
		/*
		 * Map<String, Object> payload = JwtUtil.readToken(token); String loginId =
		 * (String) payload.get("id");
		 * 
		 * if(loginId != null && loginId.equals(params.get("id"))) {
		 */
			boolean success = service.comment_insert(params);
			resp.put("success", success);
			login = true;
		//}
		
		// resp.put("loginYN", login);
		
		return resp;
	}
	
	//댓글 수정
	@PutMapping(value="/comment_update")
	public Map<String, Object> comment_update(@RequestBody Map<String, String> params,
			@RequestHeader Map<String, String> header){
		
		log.info("params : " + params);
		/* log.info("header : {} " , header); */
		resp = new HashMap<String, Object>();
		boolean login = false;
		/*
		 * String token = header.get("authorization");
		 * 
		 * Map<String, Object> payload = JwtUtil.readToken(token); String loginId =
		 * (String) payload.get("id");
		 * 
		 * if(loginId != null && loginId.equals(params.get("user_id"))) {
		 */
			boolean success = service.comment_update(params);
			resp.put("success", success);
			login = true;
		//}
		/* resp.put("loginYN", login); */
		
		return resp;
	}
	
	//댓글 삭제
	@DeleteMapping(value="/comment_del")
	public Map<String, Object> comment_del(@RequestBody Map<String, String> params, 
			@RequestHeader Map<String, String> header){
	
		log.info("params : " + params);
		/* log.info("header : {} " , header); */
		resp = new HashMap<String, Object>();
		boolean login = false;
	/*	String token = header.get("authorization");
		
		Map<String, Object> payload = JwtUtil.readToken(token);
		String loginId = (String) payload.get("id");
		
		if(loginId != null && loginId.equals(params.get("user_id"))) {*/
			boolean success = service.comment_del(params);
			resp.put("success", success);
			login = true;
			/*
			 * } resp.put("loginYN", login);
			 */
		
		return resp;
	}
}
