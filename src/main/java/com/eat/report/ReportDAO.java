package com.eat.report;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eat.dto.CourseDTO;
import com.eat.dto.MainDTO;
import com.eat.dto.MsgDTO;
import com.eat.dto.PhotoDTO;
import com.eat.dto.ReportDTO;
import com.eat.dto.ReportHistoryDTO;

@Mapper
public interface ReportDAO {

	// 신고 글 작성
	int report_write(ReportDTO content);
	
	// 신고 목록 불러오기
	List<ReportDTO> report_list(int offset, int content_cnt);
	
	// 신고 상세보기
	ReportDTO report_detail(int report_idx); // 신고 상세보기 몸통
	CourseDTO report_course(int reported_idx); // 신고 상세보기 - 신고된 코스 정보
	MsgDTO report_msg(int reported_idx); // 신고 상세보기 - 신고된 쪽지 정보
	MainDTO report_cmt(int reported_idx); // 신고 상세보기 - 신고된 댓글 정보
	PhotoDTO photo(int img_idx); // 신고 상세보기 - 이미지
	
	// 히스토리 작성
	int history_write(ReportHistoryDTO content);

	// 히스토리 불러오기
	List<ReportHistoryDTO> history_list(int report_idx, int offset, int content_cnt);

	// 신고 목록 총 페이지
	int report_pages(int content_cnt);
	
	// 히스토리 총 페이지
	int his_pages(int content_cnt, int report_idx);

	// 신고 이미지 저장
	int saveReportImg(Map<String, Object> param);

}
