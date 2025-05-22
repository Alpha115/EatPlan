package com.eat.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.dto.CourseDTO;
import com.eat.dto.MainDTO;
import com.eat.dto.MsgDTO;
import com.eat.dto.PhotoDTO;
import com.eat.dto.ReportDTO;
import com.eat.dto.ReportHistoryDTO;

@Service
public class ReportService {
	
	@Autowired ReportDAO dao;
	Map<String, Object> resp = null;
	private int content_cnt = 10;

	// 신고 목록 불러오기
	public List<ReportDTO> report_list(int page) {
		int offset = (page - 1)*content_cnt;
		return dao.report_list(offset, content_cnt);
	}
	
	// 신고 상세보기 몸통
	public ReportDTO report_detail(int report_idx) {
		return dao.report_detail(report_idx);
	}
	// 신고 상세보기 - 신고된 코스 정보
	public CourseDTO report_course(int reported_idx) {
		return dao.report_course(reported_idx);
	}
	// 신고 상세보기 - 신고된 쪽지 정보
	public MsgDTO report_msg(int reported_idx) {
		return dao.report_msg(reported_idx);
	}
	// 신고 상세보기 - 신고된 댓글 정보
	public MainDTO report_cmt(int reported_idx) {
		return dao.report_cmt(reported_idx);
	}
	// 신고 상세보기 - 이미지
	public PhotoDTO photo(int img_idx) {
		return dao.photo(img_idx);
	}
	
	// 히스토리 작성
	public boolean history_write(ReportHistoryDTO content) {
		int row = dao.history_write(content);
		return row>0;
	}

	// 히스토리 불러오기
	public List<ReportHistoryDTO> history_list(int report_idx, int page) {
		int offset = (page - 1) * content_cnt;
		return dao.history_list(report_idx, offset, content_cnt);
	}

	// 신고 목록 총 페이지
	public int report_pages() {
		return dao.report_pages(content_cnt);
	}
	
	// 히스토리 총 페이지
	public int his_pages(int report_idx) {
		return dao.his_pages(content_cnt, report_idx);
	}





}
