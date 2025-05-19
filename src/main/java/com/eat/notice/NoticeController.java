package com.eat.notice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class NoticeController {
	
	@Autowired NoticeService service;
	@Autowired NoticeDTO dto;
	Map<String, Object> resp=null;
	
	// 공지사항을 불러옵니다.
	@GetMapping("/notice")
	public Map<String, Object> noticeList(@RequestHeader Map<String, String> header){
		resp=new HashMap<String, Object>();
		
		return resp;
	}
	
	

}
