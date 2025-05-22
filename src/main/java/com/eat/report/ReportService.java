package com.eat.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.dto.ReportHistoryDTO;

@Service
public class ReportService {
	
	@Autowired ReportDAO dao;
	Map<String, Object> resp = new HashMap<String, Object>();
	private int history_cnt = 10;

	// 히스토리 작성
	public boolean history_write(ReportHistoryDTO content) {
		int row = dao.history_write(content);
		return row>0;
	}

	// 히스토리 불러오기
	public List<ReportHistoryDTO> history_list(int report_idx, int page) {
		int offset = (page - 1) * history_cnt;
		return dao.history_list(report_idx, offset, history_cnt);
	}

	// 총 페이지
	public int pages(int report_idx) {
		return dao.pages(history_cnt, report_idx);
	}




}
