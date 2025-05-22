package com.eat.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eat.dto.MainDTO;

@Service
public class CommentService {
	@Autowired
	CommentDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());
	private int comment_count = 10;
	
	// 댓글 작성
	public boolean comment_insert(Map<String, String> params) {
		int row = dao.comment_insert(params);

		return row > 0;
	}

	// 댓글 수정
	public boolean comment_update(Map<String, String> params) {
		int row = dao.comment_update(params);
		return row > 0;
	}

	// 댓글 삭제
	public boolean comment_del(Map<String, String> params) {
		int commentIdx = Integer.parseInt(params.get("comment_idx"));
		int row = dao.comment_del(commentIdx);
		return row > 0;
	}
	
	//댓글 리스트
	public Map<String, Object> comment_list(int post_idx, int page) {
		Map<String, Object> resp = new HashMap<String, Object>();
		
		int offset = (page - 1) * comment_count;
		
		List<MainDTO> comments = dao.comment_list(post_idx, offset,comment_count);
		
		resp.put("post_idx", post_idx);
		resp.put("page", page);
		resp.put("comments", comments);
		resp.put("count", comments.size());
		
		return resp;
	}
	
	// 댓글 리스트
	
	
	
	
	
	
	
	

}
