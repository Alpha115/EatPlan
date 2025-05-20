package com.eat.regist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eat.main.CourseDTO;
import com.eat.main.CourseTagDTO;
import com.eat.main.DetailCmtDTO;
import com.eat.main.DetailRestaDTO;
import com.eat.main.TimelineDTO;

@Mapper
public interface RegistDAO {

	// 코스 작성 (제대로 안돌아감)
	int regist_write(CourseDTO content);

	int regist_detailrest(List<DetailRestaDTO> content_detail, int idx);

	int regist_detailcmt(DetailCmtDTO content_detailcmt, int idx);

	int regist_tag(CourseTagDTO tag, int idx);

	int regist_time(TimelineDTO time, int idx);







}
