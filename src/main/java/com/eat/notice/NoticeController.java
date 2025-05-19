package com.eat.notice;

import java.util.ArrayList;
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

	@Autowired
	NoticeService service;
	Map<String, Object> resp = null;

	// 공지사항 리스트를 불러옵니다.
	@GetMapping("/notice")
	public Map<String, Object> list(@RequestHeader Map<String, String> header) {
		resp = new HashMap<String, Object>();
		ArrayList<NoticeDTO> noticeList = service.list();
		resp.put("noticeList", noticeList);
		return resp;
	}

}
