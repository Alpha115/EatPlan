package com.eat.main;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainDAO {

	//댓글 작성
	int comment_insert(Map<String, String> params);
	
	//댓글 수정
	int comment_update(Map<String, String> params);

}
