package com.eat.notice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class NoticeController {

	@Autowired
	NoticeService service;
	Map<String, Object> resp = null;

	// 공지사항 리스트를 불러옵니다.
	@GetMapping("/notice/{page}")
	public Map<String, Object> list(@RequestHeader Map<String, String> header, @PathVariable String page) {
		resp = new HashMap<String, Object>();
		Map<String, Object> noticeList = service.list(page);
		resp.put("noticeList", noticeList);
		return resp;
	}
	
	// 공지사항 상세보기 기능입니다.
	@GetMapping("/notice_detail/{notice_idx}")
	public Map<String, Object> detail(@PathVariable String notice_idx){
		resp=new HashMap<String, Object>();
		NoticeDTO dto=service.detail(notice_idx);
		resp.put("detail", dto);
		return resp;
	}

	// 공지사항을 작성합니다.
	@PostMapping("/notice_write")
	public Map<String, Object> write(@RequestBody Map<String, Object> params) {
		resp = new HashMap<String, Object>();
		boolean success = service.write(params);
		resp.put("success", success);
		return resp;
	}
	
	// 공지사항을 수정합니다.
	@PutMapping("/notice_update")
	public Map<String, Object> update(@RequestBody NoticeDTO params){
		resp=new HashMap<String, Object>();
		boolean success = service.update(params);
		resp.put("success", success);
		return resp;
	}
	
	//공지사항을 삭제합니다.
	@DeleteMapping("/notice_del/{notice_idx}")
	public Map<String, Object> delete(@PathVariable String notice_idx){
		resp=new HashMap<String, Object>();
		boolean success=service.delete(notice_idx);
		resp.put("success", success);
		return resp;
	}

}
