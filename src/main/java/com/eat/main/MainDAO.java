package com.eat.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainDAO {

	//댓글 작성
	int comment_insert(Map<String, String> params);
	
	//댓글 수정
	int comment_update(Map<String, String> params);
	
	//댓글 삭제
	int comment_del(int comment_del);

	// 코스 리스트 불러오기 수정필요
	List<MainDTO> course_list(int offset, int limit);

	// 코스 페이지 처리 
	int pages(int limit);

	// 코스 태그 불러오기 수정필요
	String course_tag(int idx);

	// 코스 사진 불러오기 수정필요
	String photo(int idx);



}
