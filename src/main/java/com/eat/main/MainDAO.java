package com.eat.main;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainDAO {

	//댓글 작성
	int insert(Map<String, String> params);

}
