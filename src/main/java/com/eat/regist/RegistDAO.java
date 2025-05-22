package com.eat.regist;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eat.dto.CourseDTO;
import com.eat.dto.CourseTagDTO;
import com.eat.dto.DetailCmtDTO;
import com.eat.dto.DetailRestaDTO;
import com.eat.dto.TimelineDTO;

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

	// 코스 작성 임시저장 불러오기
	List<CourseDTO> regist_tmp_list(String user_id, int offset, int limit);

	// 코스 작성 임시저장 게시물 수
	int regist_tmp_cnt(String user_id);
	
	// 코스 작성 임시저장 불러오기 페이지
	int pages(int limit);

	// 코스 수정 몸통
	int update(CourseDTO content, int post_idx);

	// 코스 수정 타임라인
	int update_time(TimelineDTO time, int post_idx);

	// 코스 수정 세부일정-식당 새로 작성
	int update_detail_resta(DetailRestaDTO d_resta, int post_idx);

	// 코스 세부일정 - 식당 삭제
	int delete_detail_resta(int detail_idx);
	
	// 코스 수정 세부일정-코멘트 새로 작성
	int update_detail_cmt(DetailCmtDTO d_cmt, int post_idx);
	
	// 코스 세부일정 - 코멘트 삭제
	int delete_detail_cmt(int detail_idx);

	// 코스 수정 코스태그 새로 생성
	int update_tags(CourseTagDTO t, int post_idx);

	// 코스 태그 삭제
	void delete_tags(CourseTagDTO t, int post_idx);

	// 코스 삭제
	int delete(List<CourseDTO> del_idx);
	
	//코스 상세보기
	public CourseDTO getCourseDTO(int post_idx);
	public TimelineDTO getTimelineDTO(int post_idx);
	public List<DetailRestaDTO> getDetailRestaList(int post_idx);
	public List<DetailCmtDTO> getCmtDTOList(int post_idx);
	public List<CourseTagDTO> getCourseList(int post_idx);





}
