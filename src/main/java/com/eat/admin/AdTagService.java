package com.eat.admin;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdTagService {

	@Autowired
	AdTagDAO dao;
	Logger log = LoggerFactory.getLogger(getClass());
	private int limit = 8, page = 0; // 1페이지당 뜨는 식당의 갯수입니다.

	public Map<String, Object> restaList(String page) {
		Map<String, Object> result = new HashMap<String, Object>();

		// 페이징 처리를 위한 코드입니다.
		// result map에는 "page":페이지 / "list" / "pages" :total page가 들어갑니다.
		this.page = Integer.parseInt(page);
		result.put("page", this.page);
		int offset = (this.page - 1) * limit;
		result.put("list", dao.restaList(offset, limit));
		result.put("pages", dao.pages(limit));
		return result;
	}

}
