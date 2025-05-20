package com.eat.regist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.main.CourseDTO;
import com.eat.main.CourseTagDTO;
import com.eat.main.DetailCmtDTO;
import com.eat.main.DetailRestaDTO;
import com.eat.main.TimelineDTO;

@Service
public class RegistService {

	@Autowired
	RegistDAO dao;

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
        for (DetailRestaDTO d_resta : content_detail_resta) {
            d_resta.setPost_idx(idx);
            d_row += dao.regist_detail_resta(d_resta);
        }
        // 2) 세부일정-코멘트
        for (DetailCmtDTO d_cmt : content_detail_cmt) {
            d_cmt.setPost_idx(idx);
            d_row += dao.regist_detail_cmt(d_cmt);
        }
        // 3) 코스태그
        for (CourseTagDTO t : tags) {
            t.setPost_idx(idx);
            t_row += dao.regist_tags(t);
        }

        if (row > 1 && d_row > 1 && t_row >1) {
			success = true;
		}

		return success;
	}

}
