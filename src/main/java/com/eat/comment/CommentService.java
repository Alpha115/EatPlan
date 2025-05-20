package com.eat.comment;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	@Autowired
	CommentDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());
	private int limit = 10, page = 0; // 1페이지당 뜨는 코스 게시물의 갯수입니다.

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

	// 댓글 리스트
	public Map<String, Object> comment_list(Map<String, String> params) {
		Map<String, Object> resp = new HashMap<String, Object>();
		resp.put("page", page);
		int offset = (page - 1) * limit;

		resp.put("comment_list", dao.comment_list(offset, limit));
		resp.put("pages", dao.pages(limit));

		return resp;
	}

}
