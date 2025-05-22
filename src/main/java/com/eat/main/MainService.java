package com.eat.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.dto.CourseDTO;
import com.eat.dto.CourseTagDTO;

@Service
public class MainService {

	@Autowired
	MainDAO dao;
	Logger logger = LoggerFactory.getLogger(getClass());
	private int limit = 10, page = 0; // 1페이지당 뜨는 코스 게시물의 갯수입니다.

	// 코스 리스트 불러오기
	public Map<String, Object> course_list(int page) {
		Map<String, Object> resp = new HashMap<String, Object>();
		this.page = page;
		resp.put("page", this.page);
		int offset = (this.page - 1) * limit;
		resp.put("list", dao.course_list(offset, limit));
		resp.put("pages", dao.pages(limit));
		return resp;
	}

	// 코스 리스트 닉네임 불러오기
	public Map<String, Object> course_list_nick(String id) {
		return dao.course_list_nick(id);
	}

	// 코스 리스트 댓글 수 불러오기
	public Map<String, Object> course_list_cmtcnt(String idx) {
		return dao.course_list_cmtcnt(idx);
	}

	// 코스 리스트 좋아요 수 불러오기
	public Map<String, Object> course_list_likecnt(String idx) {
		return dao.course_list_likecnt(idx);
	}

	// 코스 리스트 별점 평균 불러오기
	public Map<String, Object> course_list_staravg(String idx) {
		return dao.course_list_staravg(idx);
	}

	// 코스 리스트 일반 태그 불러오기
	public Map<String, Object> course_list_tag(String idx) {
		return dao.course_list_tag(idx);
	}

	// 코스 리스트 지역 태그 불러오기
	public Map<String, Object> course_list_tagarea(String idx) {
		return dao.course_list_tagarea(idx);
	}

	// 코스 리스트 사진 불러오기
	public Map<String, Object> course_list_img(String idx) {
		return dao.course_list_img(idx);
	}
	
	//코스검색
	public List<CourseDTO> search_course(String subject, String user_id, String tag) {
		List<CourseDTO> resp = dao.search_course(subject, user_id, tag);
		
		for (CourseDTO course : resp) {
			List<CourseTagDTO> tags = dao.getTags(course.getPost_idx());
			course.setTags(tags);
		}
		return resp;
	}

}
