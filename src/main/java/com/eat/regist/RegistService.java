package com.eat.regist;

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
import com.eat.tags.TagAreaDTO;
import com.eat.tags.TagDTO;

@Service
public class RegistService {

	@Autowired
	RegistDAO dao;
	Logger log = LoggerFactory.getLogger(getClass());

	// 코스 작성
	public boolean regist_write(CourseDTO content,
			List<DetailRestaDTO> content_detail_resta,
			List<DetailCmtDTO> content_detail_cmt,
			List<CourseTagDTO> tags,
			TimelineDTO time) {
		int row = dao.regist_write(content);
		int idx = content.getPost_idx();
		row += dao.regist_time(time, idx);
		int d_row = 0;
		int t_row = 0;
        boolean success = false;
        
		// 1) 세부일정-식당
        if (content_detail_resta != null) {
        	for (DetailRestaDTO d_resta : content_detail_resta) {
        		d_resta.setPost_idx(idx);
        		d_row += dao.regist_detail_resta(d_resta);
        	}
		}
        // 2) 세부일정-코멘트
        if (content_detail_cmt != null) {
        	for (DetailCmtDTO d_cmt : content_detail_cmt) {
        		d_cmt.setPost_idx(idx);
        		d_row += dao.regist_detail_cmt(d_cmt);
        	}
		}
        // 3) 코스태그
        if (tags != null) {
        	for (CourseTagDTO t : tags) {
        		t.setPost_idx(idx);
        		t_row += dao.regist_tags(t);
        	}
		}

        if (row > 1 && d_row > 0 && t_row >0) {
			success = true;
		}

		return success;
	}

	private int limit = 6, page = 0; // 1페이지당 뜨는 코스 게시물의 갯수입니다.
	
	// 코스 작성 임시저장 불러오기
	public Map<String, Object> regist_tmp_list(String user_id, int page) {
		Map<String, Object> resp = new HashMap<String, Object>();
		this.page = page;
		resp.put("page", this.page);
		int offset = (this.page - 1) * limit;
		
		resp.put("cnt", dao.regist_tmp_cnt(user_id));
		resp.put("list", dao.regist_tmp_list(user_id, offset, limit));
		resp.put("pages", dao.pages(limit));
		return resp;
	}

	
	// 코스 수정 할 게시글 불러오기 
	public RegistRequestDTO regist_update_content(int post_idx) {
		
		RegistRequestDTO resp = new RegistRequestDTO();
		CourseDTO course = dao.course_content(post_idx);
		TimelineDTO timeline = dao.course_timeline(post_idx);
		List<DetailRestaDTO> restaList = dao.course_detail_resta(post_idx);
		List<DetailCmtDTO> cmtList = dao.course_detail_cmt(post_idx);
		List<CourseTagDTO> tagList = dao.course_tag_idx(post_idx);
		List<TagDTO> tagListResult = new ArrayList<>();
		List<TagAreaDTO> tagAreaListResult = new ArrayList<>();
		
		if (tagList != null && tagList.size() > 0) {
			for (CourseTagDTO tag_info : tagList) {
			    String isClass = tag_info.getIsClass();

			    if ("area_tag".equals(isClass)) {
			        tagAreaListResult.addAll(dao.course_tag_area(tag_info.getIdx()));
			    } else if ("tag".equals(isClass)) {
			        tagListResult.addAll(dao.course_tag(tag_info.getIdx()));
			    }
			}
		}
		
		resp.setContent(course);
		resp.setTime(timeline);
		resp.setContent_detail_resta(restaList);
		resp.setContent_detail_cmt(cmtList);
		resp.setTags(tagList);
		resp.setTag_name(tagListResult);
		resp.setTag_name_area(tagAreaListResult);
		
		return resp;
	}
	 
	
	// 코스 수정
	public boolean update(
			CourseDTO content,
			List<DetailRestaDTO> content_detail_resta,
			List<DetailRestaDTO> content_detail_resta_del,
			List<DetailCmtDTO> content_detail_cmt,
			List<DetailCmtDTO> content_detail_cmt_del,
			List<CourseTagDTO> tags,
			List<CourseTagDTO> tags_del,
			TimelineDTO time,
			int post_idx) {
		
		int row = dao.update(content, post_idx);
		row += dao.update_time(time, post_idx);
		
		int d_row = 0;
		int t_row = 0;
        boolean success = false;
        int detail_idx = 0;
        
		// 1) 세부일정-식당 삭제
        if (detail_idx >0) {
            for (DetailRestaDTO d_resta : content_detail_resta_del) {
            	detail_idx = d_resta.getDetail_idx();
                dao.delete_detail_resta(detail_idx);
    		}
		}
        // 1) 세부일정-식당 새로 작성
        for (DetailRestaDTO d_resta : content_detail_resta) {
            d_row += dao.update_detail_resta(d_resta, post_idx);
        }

        // 2) 세부일정-코멘트 삭제
        if (detail_idx>0) {
            for (DetailCmtDTO d_cmt : content_detail_cmt_del) {
            	detail_idx = d_cmt.getDetail_idx();
                dao.delete_detail_cmt(detail_idx);
    		}
		}
        // 2) 세부일정-코멘트 새로 작성
        for (DetailCmtDTO d_cmt : content_detail_cmt) {
            d_row += dao.update_detail_cmt(d_cmt, post_idx);
        }
        // 3) 코스태그 삭제
        if (tags_del != null) {
            for (CourseTagDTO t : tags_del) {
                dao.delete_tags(t, post_idx);
            }
		}
        
        // 3) 코스태그 새로 작성
        for (CourseTagDTO t : tags) {
            t_row += dao.update_tags(t, post_idx);
        }

        if (row > 1 && d_row > 0 && t_row >0) {
			success = true;
		}

		return success;
		
	}

	// 코스 삭제
	public boolean delete(List<CourseDTO> del_idx) {
		int del_cnt = del_idx.size();
		log.info("del_idx 사이즈 : "+ del_cnt);
		int row = 0;
		if (del_cnt>0) {
			row = dao.delete(del_idx);
		}
		return row == del_cnt && row > 0;
	}

}
