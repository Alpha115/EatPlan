package com.eat.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.dto.CourseDTO;
import com.eat.dto.CourseTagDTO;
import com.eat.dto.DetailCmtDTO;
import com.eat.dto.DetailRestaDTO;
import com.eat.dto.RegistRequestDTO;
import com.eat.dto.TimelineDTO;

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
		List<CourseDTO> list = dao.course_list(offset, limit);
		
		List<Map<String, Object>> result_list = new ArrayList<Map<String,Object>>();
		
		if (list != null) {
			for (CourseDTO content : list) {
				Map<String, Object> course_data = new HashMap<String, Object>();
				course_data.put("course", content);
				course_data.put("nickname", dao.course_list_nick(content.getUser_id()));
				course_data.put("cmt_cnt", dao.course_list_cmt_cnt(content.getPost_idx()));
				course_data.put("like_cnt", dao.course_list_like_cnt(content.getPost_idx()));
				course_data.put("star_avg", dao.course_list_star_avg(content.getPost_idx()));
				course_data.put("course_tag", dao.course_list_tag(content.getPost_idx()));
				course_data.put("course_tag_area", dao.course_list_tag_area(content.getPost_idx()));
				List<DetailRestaDTO> detail = dao.detail(content.getPost_idx());
				if (detail != null && !detail.isEmpty()) {
					DetailRestaDTO first_detail = detail.get(0);
					course_data.put("course_img", dao.course_list_img(first_detail.getDetail_idx()));
				}
				result_list.add(course_data);
			}
		}
		
		resp.put("list", result_list);
		resp.put("page", this.page);
		resp.put("pages", dao.pages(limit));
		return resp;
	}
	
	// 코스 상세보기
	public RegistRequestDTO courseDetail(int post_idx) {
		
		CourseDTO course = dao.getCourseDTO(post_idx);
		TimelineDTO timeline = dao.getTimelineDTO(post_idx);
		List<DetailRestaDTO> restaList = dao.getDetailRestaList(post_idx);
		List<DetailCmtDTO> cmtList = dao.getCmtDTOList(post_idx);
		List<CourseTagDTO> tagList = dao.getCourseList(post_idx);
		
		RegistRequestDTO resp = new RegistRequestDTO();
		resp.setContent(course);
		resp.setTime(timeline);
		resp.setContent_detail_resta(restaList);
		resp.setContent_detail_cmt(cmtList);
		resp.setTags(tagList);
		
		return resp;
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
