package com.eat.admin;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AdBlindController {
	
	@Autowired
	AdBlindService service;
	
	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());
	
	//코스 블라인드
	@PatchMapping(value="/{post_idx}/course_blind")
	public Map<String, Object>course_blind(@PathVariable int post_idx){
		
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean success = service.course_blind(post_idx);
		resp.put("success", success);
		
		return resp;
	}
	
	//게시글 댓글 블라인드
	@PatchMapping(value="/{post_idx}/{comment_idx}/{user_id}/comment_blind")
	public Map<String, Object>comment_blind(@PathVariable int post_idx,
			@PathVariable int comment_idx,
			@PathVariable String user_id){
		
		Map<String, Object> resp = new HashMap<String, Object>();
		boolean success = service.comment_blind(comment_idx,post_idx,user_id);
		resp.put("success", success);
		
		return resp;
	}
}
