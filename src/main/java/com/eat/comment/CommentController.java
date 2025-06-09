package com.eat.comment;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eat.admin.AdminAuth;
import com.eat.utils.JwtUtil;

@CrossOrigin
@RestController
public class CommentController {

	@Autowired
	CommentService service;
	@Autowired
	AdminAuth auth;

	Map<String, Object> resp = null;
	Logger log = LoggerFactory.getLogger(getClass());

	// 댓글 리스트
	@GetMapping(value = "/comment_list")
	public Map<String, Object> comment_list(@RequestParam int post_idx, @RequestParam(defaultValue = "1") int page) {
		resp = new HashMap<String, Object>();

		int totalCount = service.comment_total_count(post_idx);
		
		resp.put("list", service.comment_list(post_idx, page));
		resp.put("totalCount", totalCount);

		return resp;
	}

	// 댓글 작성
	@PostMapping(value = "/comment_insert")
	public Map<String, Object> comment_insert(@RequestBody Map<String, String> params,
			@RequestHeader Map<String, String> header) {
		resp = new HashMap<String, Object>();

		String loginId = (String) JwtUtil.readToken(header.get("authorization")).get("user_id");
		
		if (loginId != null && !loginId.equals("") && loginId.equals(params.get("user_id"))) {
			boolean success = service.comment_insert(params);
			resp.put("success", success);
		} else {
			resp.put("success", false);
		}
		
		return resp;
	}

	// 댓글 수정
	@PutMapping(value = "/comment_update")
	public Map<String, Object> comment_update(@RequestBody Map<String, String> params,
			@RequestHeader Map<String, String> header) {
		resp = new HashMap<String, Object>();
		
		String loginId = (String) JwtUtil.readToken(header.get("authorization")).get("user_id");
		if (loginId != null && !loginId.equals("") && loginId.equals(params.get("user_id"))) {
			boolean success = service.comment_update(params);
			resp.put("success", success);
		} else {
			resp.put("success", false);
		}

		return resp;
	}

	// 댓글 삭제
	@DeleteMapping(value = "/comment_del")
	public Map<String, Object> comment_del(@RequestParam("comment_idx") int comment_idx,
			@RequestHeader Map<String, String> header) {

		log.info("comment_idx : " + comment_idx);
		resp = new HashMap<String, Object>();

		// user_id 어디감?
		String loginId = (String) JwtUtil.readToken(header.get("authorization")).get("user_id");
		
		if (loginId != null && !loginId.equals("")) {
			boolean success = service.comment_del(comment_idx);
			resp.put("success", success);
		}


		return resp;
	}
	
	// -----------댓글 세부내용 불러오기(관리자-신고히스토리 열람에 필요)
	@GetMapping("/comment_detail/{comment_idx}")
	public Map<String, Object> comment_detail(@PathVariable String comment_idx){
		resp=service.comment_detail(comment_idx);
		return resp;
	}
}
