package com.eat.report;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.eat.dto.ReportHistoryDTO;

@Mapper
public interface ReportDAO {

	// 히스토리 작성
	int history_write(ReportHistoryDTO content);

	// 히스토리 불러오기
	List<ReportHistoryDTO> history_list(int report_idx, int offset, int history_cnt);

	// 총 페이지
	int pages(int history_cnt, int report_idx);

}
