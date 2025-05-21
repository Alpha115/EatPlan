package com.eat.admin;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdBlindDAO {
	
	//코스 블라인드
	int course_blind(int post_idx);
	
	//게시글 댓글 블라인드
	int comment_blind(int comment_idx, int post_idx, String user_id);

}
