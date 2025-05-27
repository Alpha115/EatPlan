package com.eat.comment;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.MainDTO;

@CrossOrigin
@RestController
public class CommentController {

	@Autowired
	CommentService service;

	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());

	 // 댓글 리스트
	   @GetMapping(value = "/comment_list")
	   public Map<String, Object> comment_list(@RequestParam int post_idx,
			   @RequestParam (defaultValue = "1") int page){
		   
		   Map<String, Object> resp = service.comment_list(post_idx, page);
		   
		   return resp;
	   }

	   //댓글 작성
	      @PostMapping(value = "/comment_insert")
	      public Map<String, Object> comment_insert (@RequestBody Map<String, String> params
	            /*@RequestHeader Map<String, String> header*/){
	         
	         log.info("user_id: " + params.get("user_id"));
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
	            boolean success = service.comment_insert(params);
	            resp.put("success", success);
	            /* login = true; */
	         //}
	         
	         // resp.put("loginYN", login);
	         
	         return resp;
	      }

	   // 댓글 수정
	   @PutMapping(value = "/comment_update")
	   public Map<String, Object> comment_update(@RequestBody Map<String, String> params) {

	      log.info("params : " + params);
	      resp = new HashMap<String, Object>();
	     
	      boolean success = service.comment_update(params);
	      resp.put("success", success);

	      return resp;
	   }

	   // 댓글 삭제
	   @DeleteMapping(value = "/comment_del")
	   public Map<String, Object> comment_del(@RequestParam("comment_idx") int comment_idx) {

	      log.info("comment_idx : " + comment_idx);
	      resp = new HashMap<String, Object>();
	    
	      boolean success = service.comment_del(comment_idx);
	      resp.put("success", success);

	      return resp;
	   }
	}
