package com.eat.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdBlindDAO {
	
	//코스 블라인드
	int course_blind(int post_idx);
	
	//게시글 댓글 블라인드
	int comment_blind(@Param("comment_idx") int comment_idx, 
			@Param("post_idx") int post_idx, 
			@Param("user_id")String user_id);

}
