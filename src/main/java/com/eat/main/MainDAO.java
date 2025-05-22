package com.eat.main;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eat.dto.CourseDTO;
import com.eat.dto.CourseTagDTO;

@Mapper
public interface MainDAO {

	// 코스 리스트 불러오기
	List<CourseDTO> course_list(int offset, int limit);
	
	// 코스 리스트 페이지 처리 
	int pages(int limit);

	// 코스 리스트 닉네임 불러오기
	Map<String, Object> course_list_nick(String id);

	// 코스 리스트 댓글 수 불러오기
	Map<String, Object> course_list_cmtcnt(String idx);

	// 코스 리스트 좋아요 수 불러오기
	Map<String, Object> course_list_likecnt(String idx);

	// 코스 리스트 별점 평균 불러오기
	Map<String, Object> course_list_staravg(String idx);

	// 코스 리스트 일반 태그 불러오기
	Map<String, Object> course_list_tag(String idx);

	// 코스 리스트 지역 태그 불러오기
	Map<String, Object> course_list_tagarea(String idx);
	
	// 코스 리스트 이미지 불러오기
	Map<String, Object> course_list_img(String idx);
	
	//코스 검색
	List<CourseDTO> search_course(String subject, String user_id, String tag);
	
	//코스 태그 가져오기
	List<CourseTagDTO> getTags(int post_idx);

}
