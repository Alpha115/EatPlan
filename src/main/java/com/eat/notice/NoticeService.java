package com.eat.notice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

	@Autowired
	NoticeDAO dao;
	Logger log = LoggerFactory.getLogger(getClass());
	private int limit = 8, page = 0; // 1페이지당 뜨는 공지사항 게시물의 갯수입니다.

	public Map<String, Object> list(String page) {
		Map<String, Object> result = new HashMap<String, Object>();
		log.info("Notice/Service에서 list를 호출하였습니다.");
		this.page=Integer.parseInt(page);
		result.put("page", this.page);
		int offset = (this.page - 1) * limit;
		result.put("list", dao.list(offset, limit));
		result.put("pages", dao.pages(limit));
		
		return result;
	}

	public boolean write(Map<String, Object> params) {
		log.info("Notice/Service에서 write를 호출하였습니다.");
		int row = dao.write(params);
		return row > 0 ? true : false;
	}

	public NoticeDTO detail(String notice_idx) {
		upHit(notice_idx);
		return dao.detail(notice_idx);
	}

	// 해당 게시글의 조회수를 1 올리는 함수입니다.
	public void upHit(String idx) {
		int row = dao.upHit(idx);
		log.info("Notice/Service 에서 upHit을 호출하였습니다. / {}", row);
	}

	public boolean update(NoticeDTO params) {
		int row = dao.update(params);
		return row > 0 ? true : false;
	}

	public boolean delete(String notice_idx) {
		int row = dao.delete(notice_idx);
		return row > 0 ? true : false;
	}

}
