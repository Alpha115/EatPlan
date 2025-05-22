package com.eat.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.ReportHistoryDTO;

@CrossOrigin
@RestController
public class ReportController {
	
	@Autowired ReportService service;
	
	Map<String, Object> resp = new HashMap<String, Object>();
	Logger log = LoggerFactory.getLogger(getClass());

	// 히스토리 작성
	@PostMapping(value="/history_write")
	public Map<String, Object> history_write(
			@RequestBody ReportHistoryDTO content){
		
		boolean success = service.history_write(content);
		
		resp.put("success", success);
		
		return resp;
	}
	
	// 히스토리 불러오기
	@GetMapping(value="history_list/{report_idx}")
	public Map<String, Object> history_list(
			@PathVariable int report_idx,
			@RequestParam (defaultValue = "1") int page){
		
		List<ReportHistoryDTO> list = new ArrayList<ReportHistoryDTO>();
		list = service.history_list(report_idx, page);
		int cnt = list.size();
		int pages = service.pages(report_idx);
		
		resp.put("list", list);
		resp.put("cnt", cnt);
		resp.put("pages", pages);
		return resp;
	}
}
