package com.eat.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MainDAO {

	// 코스 리스트 불러오기
	List<CourseListDTO> course_list(int offset, int limit);
	
	// 코스 리스트 태그 불러오기
	Map<String, Object> course_list_tag(String idx);
	
	// 코스 리스트 이미지 불러오기
	Map<String, Object> course_list_img(String idx);
	
	// 코스 리스트 페이지 처리 
	int pages(int limit);
	
	//댓글 작성
	int comment_insert(Map<String, String> params);
	
	//댓글 수정
	int comment_update(Map<String, String> params);
	
	//댓글 삭제
	int comment_del(int comment_del);

}
