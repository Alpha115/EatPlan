package com.eat.regist;

import org.apache.ibatis.annotations.Mapper;

import com.eat.main.CourseDTO;
import com.eat.main.CourseTagDTO;
import com.eat.main.DetailCmtDTO;
import com.eat.main.DetailRestaDTO;
import com.eat.main.TimelineDTO;

@Mapper
public interface RegistDAO {

	// 코스 작성 몸통
	int regist_write(CourseDTO content);

	// 코스 작성 타임라인
	int regist_time(TimelineDTO time, int idx);

	// 코스 작성 세부일정-식당
	int regist_detail_resta(DetailRestaDTO d_resta);

	// 코스 작성 세부일정-코멘트
	int regist_detail_cmt(DetailCmtDTO d_cmt);

	// 코스 작성 코스태그
	int regist_tags(CourseTagDTO t);




}
