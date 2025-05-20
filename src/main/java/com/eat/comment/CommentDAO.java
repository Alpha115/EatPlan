package com.eat.comment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eat.main.MainDTO;

@Mapper
public interface CommentDAO {
	
	//댓글 작성
	int comment_insert(Map<String, String> params);
	
	//댓글 수정
	int comment_update(Map<String, String> params);
	
	//댓글 삭제
	int comment_del(int comment_del);
	
	//댓글 리스트
	List<MainDTO> comment_list(int post_idx, int offset, int comment_count);
	
	

	
	

	
	

}
