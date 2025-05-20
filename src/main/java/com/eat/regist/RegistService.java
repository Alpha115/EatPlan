package com.eat.regist;

import java.util.ArrayList;
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

	// 코스 작성 (제대로 안돌아감)
	public boolean regist_write(CourseDTO content, List<DetailRestaDTO> content_detail, DetailCmtDTO content_detailcmt, CourseTagDTO tag, TimelineDTO time) {
		int row = dao.regist_write(content);
		int idx = content.getPost_idx();
		row =
				+ dao.regist_time(time,idx)
				+ dao.regist_detailrest(content_detail, idx)
				+ dao.regist_detailcmt(content_detailcmt, idx)
				+ dao.regist_tag(tag, idx);

		return row > 0;
	}

}
