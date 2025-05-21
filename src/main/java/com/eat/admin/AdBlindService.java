package com.eat.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdBlindService {
	
	@Autowired
	AdBlindDAO dao;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	//코스 블라인드
	public boolean course_blind(int post_idx) {
		int row = dao.course_blind(post_idx);
		return row > 0;
	}
	
	//게시글 댓글 블라인드
	public boolean comment_blind(int comment_idx, int post_idx, String user_id) {
		int row = dao.comment_blind(comment_idx, post_idx, user_id);
		return row > 0;
	}
	
	
}
