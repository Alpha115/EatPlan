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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eat.dto.CourseDTO;
import com.eat.dto.MainDTO;
import com.eat.dto.MsgDTO;
import com.eat.dto.PhotoDTO;
import com.eat.dto.ReportDTO;
import com.eat.dto.ReportHistoryDTO;

@CrossOrigin
@RestController
public class ReportController {
	
	@Autowired ReportService service;
	Map<String, Object> resp = null;
	Logger log = LoggerFactory.getLogger(getClass());

	// 신고글 작성
	@PostMapping(value="/report_write")
	public Map<String, Object> report_write(
			@ModelAttribute ReportDTO content){
		
		resp = new HashMap<String, Object>();
		
		boolean success = service.report_write(content);
		resp.put("success", success);
		
		return resp;
	}
	
	// 신고 목록 불러오기
	@GetMapping(value="/report_list/{page}")
	public Map<String, Object> report_list(
			@PathVariable int page){
		
		resp = new HashMap<String, Object>();
		
		List<ReportDTO> list = service.report_list(page);
		int pages = service.report_pages();
		
		resp.put("list", list);
		resp.put("pages", pages);
		
		return resp;
	}
	
	// 신고 상세보기
	@GetMapping(value="/report_detail/{report_idx}")
	public Map<String, Object> report_detail(
			@PathVariable int report_idx){
		
		resp = new HashMap<String, Object>();
		
		
		//신고 상세보기 - 몸통
		ReportDTO detail = service.report_detail(report_idx);
		if (detail == null) {
			log.error("해당 신고가 존재하지 않습니다: " + report_idx);
			resp.put("error", "해당 신고가 존재하지 않습니다.");
		    return resp;
		}
		Integer reported_idx = detail.getReported_idx();
		String isClass = detail.getIsClass();
		Integer img_idx = detail.getImg_idx();
		
		log.info("신고받은 idx : "+reported_idx);
		log.info("신고 분류 : "+isClass);

		if (isClass == null || reported_idx == null) {
	        log.error("신고글 정보 누락: isClass=" + isClass + ", reported_idx=" + reported_idx);
	        resp.put("error", "신고 정보가 올바르지 않습니다.");
	        return resp;
	    }
		
		//신고 상세보기 - 분류
		switch (isClass) {
        case "course":
            CourseDTO reported_course = service.report_course(reported_idx);
            resp.put("reported_course", reported_course);
            break;
        case "message":
            MsgDTO reported_msg = service.report_msg(reported_idx);
            resp.put("reported_msg", reported_msg);
            break;
        case "comment":
            MainDTO reported_cmt = service.report_cmt(reported_idx);
            resp.put("reported_cmt", reported_cmt);
            break;
        default:
            log.warn("알 수 없는 신고 분류: " + isClass);
            resp.put("error", "지원되지 않는 신고 유형입니다.");
    }
		/*
		 * if (isClass != null && isClass.equals("course") ) { CourseDTO reported_course
		 * = service.report_course(reported_idx); resp.put("reported_course",
		 * reported_course); }else if (isClass != null && isClass.equals("message")) {
		 * MsgDTO reported_msg = service.report_msg(reported_idx);
		 * resp.put("reported_msg", reported_msg); }else if (isClass != null &&
		 * isClass.equals("comment")) { MainDTO reported_cmt =
		 * service.report_cmt(reported_idx); resp.put("reported_cmt", reported_cmt);
		 * }else { resp.put("reported_?", "신고 분류를 확인하세요"); }
		 */
		
		//신고 상세보기 - 이미지
		if (img_idx != null && img_idx > 0) {
			PhotoDTO photo = service.photo(img_idx);
			resp.put("photo", photo);
		}else {
			resp.put("photo", "no_image");
		}
		
		resp.put("detail", detail);
		
		return resp;
	}
	
	// 히스토리 작성
	@PostMapping(value="/history_write")
	public Map<String, Object> history_write(
			@RequestBody ReportHistoryDTO content){
		
		resp = new HashMap<String, Object>();
		
		boolean success = service.history_write(content);
		
		resp.put("success", success);
		
		return resp;
	}
	
	// 히스토리 불러오기
	@GetMapping(value="/history_list/{report_idx}")
	public Map<String, Object> history_list(
			@PathVariable int report_idx,
			@RequestParam (defaultValue = "1") int page){
		
		resp = new HashMap<String, Object>();
		
		List<ReportHistoryDTO> list = new ArrayList<ReportHistoryDTO>();
		list = service.history_list(report_idx, page);
		int cnt = list.size();
		int pages = service.his_pages(report_idx);
		
		resp.put("list", list);
		resp.put("cnt", cnt);
		resp.put("pages", pages);
		return resp;
	}
	
	// 신고 처리상태 변경
	@PutMapping(value="/report_done")
	public Map<String, Object> report_done(
			@RequestBody Map<String, String> params){
		
		resp = new HashMap<String, Object>();
		
		boolean success = service.report_done(params.get("report_idx"), params.get("done"));
		
		resp.put("success", success);
		
		return resp;
	}
}
