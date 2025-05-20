package com.eat.comment;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDAO {
	
	//댓글 작성
	int comment_insert(Map<String, String> params);
	
	//댓글 수정
	int comment_update(Map<String, String> params);
	
	//댓글 삭제
	int comment_del(int comment_del);

	// 코스 리스트 페이지 처리 
	int pages(int limit);
	
	//댓글 리스트
	Object comment_list(int offset, int limit);

}
